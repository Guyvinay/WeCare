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

import com.weCare.exceptions.NotFoundException;
import com.weCare.modals.Appointment;
import com.weCare.repository.AppointmentRepository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class ConnectionInterceptor implements HandshakeInterceptor {

	private AppointmentRepository appointmentRepository;
	
	@Override
	public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
			Map<String, Object> attributes) throws Exception {
		// TODO Auto-generated method stub
		String uri = request.getURI().toString();
		String userId = extractUserId(uri);
		String appointmentId = extractAppointmentId(uri);

		Appointment appointment = appointmentRepository.findById(appointmentId).orElse(null);
		
		if( appointment.getDoctor().getProfile_id().equals(userId)) return true;
		else if(appointment.getPatient().getProfile_id().equals(userId)) return true;
		else return false;
	}

	@Override
	public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
			Exception exception) {
		
	}

private String extractAppointmentId(String uri) {
    	
        Pattern pattern = Pattern.compile("/chat/(.*?)/(.*?)$");
        Matcher matcher = pattern.matcher(uri);
        String userId = "";
        if (matcher.find() && matcher.groupCount()==2){
            userId = matcher.group(1);
        }
        return userId;
    }
    
   private String extractUserId(String uri) {
    	
    Pattern pattern = Pattern.compile("/chat/(.*?)/(.*?)$");
    Matcher matcher = pattern.matcher(uri);
    String problemId = "";
    if (matcher.find() && matcher.groupCount()==2){
        problemId = matcher.group(2);
    }
    return problemId;
    }
	

}
