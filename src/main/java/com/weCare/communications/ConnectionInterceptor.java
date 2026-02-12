package com.weCare.communications;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.server.HandshakeInterceptor;

import com.weCare.exceptions.AppointmentNotFoundException;
import com.weCare.modals.Appointment;
import com.weCare.repository.AppointmentRepository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class ConnectionInterceptor implements HandshakeInterceptor {

	@Autowired
	private AppointmentRepository appointmentRepository;
	
	public static final Map<String, Map<String, WebSocketSession>> active_sessions = MessageWebSocketHandler.appointmentSessions;
	

	@Override
	public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
			Map<String, Object> attributes) throws Exception {
		// TODO Auto-generated method stub
		String uri = request.getURI().toString();
		String userId = extractUserId(uri);
		String appointmentId = extractAppointmentId(uri);

		Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow(
				() -> new AppointmentNotFoundException("Appointment with id: " + appointmentId + ", not found!!!"));

		if(!active_sessions.isEmpty()) {
			if( active_sessions.get(appointmentId).get(userId)!=null ) {
				if(active_sessions.get(appointmentId).get(userId).isOpen()) {
					System.err.println("Already Connected!!!");
					log.info("Already Connected");
					return false;
				}
			}
		}
		
		if (appointment.getDoctor().getProfile_id().equals(userId))
			return true;
		else if (appointment.getPatient().getProfile_id().equals(userId))
			return true;
		else
			return false;
	}

	@Override
	public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
			Exception exception) {

	}

	private String extractAppointmentId(String uri) {

		Pattern pattern = Pattern.compile("/chat/(.*?)/(.*?)$");
		Matcher matcher = pattern.matcher(uri);
		String userId = "";
		if (matcher.find() && matcher.groupCount() == 2) {
			userId = matcher.group(1);
		}
		return userId;
	}

	private String extractUserId(String uri) {

		Pattern pattern = Pattern.compile("/chat/(.*?)/(.*?)$");
		Matcher matcher = pattern.matcher(uri);
		String problemId = "";
		if (matcher.find() && matcher.groupCount() == 2) {
			problemId = matcher.group(2);
		}
		return problemId;
	}

}
