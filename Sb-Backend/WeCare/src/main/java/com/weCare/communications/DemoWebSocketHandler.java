package com.weCare.communications;

import java.io.IOException;
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
import com.weCare.modals.Appointment;
import com.weCare.repository.AppointmentRepository;
import com.weCare.repository.MessageRepository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class DemoWebSocketHandler extends TextWebSocketHandler {
	
	@Autowired
	private AppointmentRepository appointmentRepository;
	
	@Autowired
	private MessageRepository messageRepository;
	
	private static final Map<String, Map<String, WebSocketSession>> appointmentSessions = new HashMap<>();
	
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
    	
    	String userId = extractUserId(session);
    	String appointmentId = extractAppointmentId(session);
  	
    	appointmentSessions.computeIfAbsent(appointmentId, val-> new HashMap<>());
    	
    	appointmentSessions.get(appointmentId)
    	            .put(userId, session);	
    }
    
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {

    	String appointmentId = extractAppointmentId(session);
    	String userId = extractUserId(session);
    	
    	Appointment appointment = appointmentRepository.findById(appointmentId).orElse(null);
    	
    	String message_payload = message.getPayload();
    	
    	ObjectMapper objectMapper = new ObjectMapper();
    	
    	JsonNode jsonNode = objectMapper.readTree(message_payload);
    	
    	ObjectNode objectNode = (ObjectNode)jsonNode;

    	objectNode.put("sender", userId);
    	
    	String writeValueAsString = objectMapper.writeValueAsString(objectNode);
    	
    	String text_message = jsonNode.get("message").asText();
    	
//    	System.out.println(text_message);
//    	System.out.println(writeValueAsString);
    	
    	
    	
    	
    	if( appointment.getDoctor().getProfile_id().equals(userId) ) {
    		
    	}else {
    		
    	}
    	
    	String messagePayload = message.getPayload().toString();
    	
    	
    	
    	broadcastMessageToReciever(appointmentId, writeValueAsString, session );
    	
    }

    private void broadcastMessageToReciever(String appointmentId, String messagePayload, WebSocketSession sender) {
    	
    	
    	Map<String, WebSocketSession> sessions = appointmentSessions.get(appointmentId);
    	
    	if(sessions!=null) {
    		
    		for( Map.Entry<String, WebSocketSession> session: sessions.entrySet() ) {
    			if(
    					session.getValue().isOpen()&&
    					!sender.getId().equals(session.getValue().getId())) {
    				try {
						session.getValue().sendMessage(new TextMessage(messagePayload));
					} catch (IOException e) {
						// TODO Auto-generated catch block
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
    	
    	String uri= session.getUri().toString();
        Pattern pattern = Pattern.compile("/chat/(.*?)/(.*?)$");
        Matcher matcher = pattern.matcher(uri);
        String userId = "";
        if (matcher.find() && matcher.groupCount()==2){
            userId = matcher.group(1);
        }
        return userId;
    }
    
   private String extractUserId(WebSocketSession session) {
    	
    String uri= session.getUri().toString();
    Pattern pattern = Pattern.compile("/chat/(.*?)/(.*?)$");
    Matcher matcher = pattern.matcher(uri);
    String problemId = "";
    if (matcher.find() && matcher.groupCount()==2){
        problemId = matcher.group(2);
    }
    return problemId;
    }
}
