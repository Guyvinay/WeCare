package com.weCare.communications;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.weCare.exceptions.NotFoundException;
import com.weCare.modals.Appointment;
import com.weCare.modals.Message;
import com.weCare.repository.AppointmentRepository;
import com.weCare.repository.DoctorRepository;
import com.weCare.repository.MessageRepository;
import com.weCare.repository.PatientRepository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class MessageWebSocketHandler extends TextWebSocketHandler {

	@Autowired
	private AppointmentRepository appointmentRepository;

	@Autowired
	private MessageRepository messageRepository;

	private static final Map<String, Map<String, WebSocketSession>> appointmentSessions = new HashMap<>();

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {

		String userId = extractUserId(session);
		String appointmentId = extractAppointmentId(session);

		appointmentSessions.computeIfAbsent(appointmentId, val -> new HashMap<>());
		appointmentSessions.get(appointmentId).put(userId, session);
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {

		String appointmentId = extractAppointmentId(session);
		String userId = extractUserId(session);
		Message chat_message = new Message();

		Appointment appointment = appointmentRepository.findById(appointmentId)
				.orElseThrow(() -> new NotFoundException("Doctor with id: " + appointmentId + ", not found!!!"));

		String message_payload = message.getPayload();
		ObjectMapper objectMapper = new ObjectMapper();

		JsonNode jsonNode = objectMapper.readTree(message_payload);

		ObjectNode objectNode = (ObjectNode) jsonNode;

		if (appointment.getDoctor().getProfile_id().equals(userId)) {
			String doctor_name = appointment.getDoctor().getDoctor_name();
			objectNode.put("Sender", doctor_name);
			objectNode.put("By", "Doctor");
			chat_message.setSender(doctor_name);
		} else {
			String patient_name = appointment.getPatient().getPatient_name();
			objectNode.put("Sender", patient_name);
			objectNode.put("By", "Patient");
			chat_message.setSender(patient_name);
		}

		String writeValueAsString = objectMapper.writeValueAsString(objectNode);

		String text_message = jsonNode.get("message").asText();

		chat_message.setAppointment(appointment);
		chat_message.setDoctor(appointment.getDoctor());
		chat_message.setPatient(appointment.getPatient());
		chat_message.setMessage(text_message);
		chat_message.setTimeStamp(LocalDateTime.now());

		messageRepository.save(chat_message);

		broadcastMessageToReciever(appointmentId, writeValueAsString, session);

	}

	private void broadcastMessageToReciever(String appointmentId, String messagePayload, WebSocketSession sender) {

		Map<String, WebSocketSession> sessions = appointmentSessions.get(appointmentId);
		String userId = extractUserId(sender);
		if (sessions != null) {

			for (Map.Entry<String, WebSocketSession> session : sessions.entrySet()) {
//				System.out.println(session);
				if (session.getValue().isOpen() && !sender.getId().equals(session.getValue().getId())
						&& !userId.equals(session.getKey())) {
					try {
						session.getValue().sendMessage(new TextMessage(messagePayload));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}

		}
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
		String appointmentId = extractAppointmentId(session);
		String userId = extractUserId(session);

		appointmentSessions.get(appointmentId).remove(userId);
	}

	private String extractAppointmentId(WebSocketSession session) {

		String uri = session.getUri().toString();
		Pattern pattern = Pattern.compile("/chat/(.*?)/(.*?)$");
		Matcher matcher = pattern.matcher(uri);
		String userId = "";
		if (matcher.find() && matcher.groupCount() == 2) {
			userId = matcher.group(1);
		}
		return userId;
	}

	private String extractUserId(WebSocketSession session) {

		String uri = session.getUri().toString();
		Pattern pattern = Pattern.compile("/chat/(.*?)/(.*?)$");
		Matcher matcher = pattern.matcher(uri);
		String problemId = "";
		if (matcher.find() && matcher.groupCount() == 2) {
			problemId = matcher.group(2);
		}
		return problemId;
	}
}
