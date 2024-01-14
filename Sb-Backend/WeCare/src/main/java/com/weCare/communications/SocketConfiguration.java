package com.weCare.communications;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.weCare.repository.AppointmentRepository;
import com.weCare.repository.DoctorRepository;
import com.weCare.repository.MessageRepository;
import com.weCare.repository.PatientRepository;

@Configuration
@EnableWebSocket
public class SocketConfiguration implements WebSocketConfigurer {
	
	@Autowired
	private AppointmentRepository appointmentRepository;
	
	@Autowired
	private MessageRepository messageRepository;
	
	@Autowired
	private DoctorRepository doctorRepository;
	
	@Autowired
	private PatientRepository patientRepository;
	
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new DemoWebSocketHandler(appointmentRepository, messageRepository, doctorRepository, patientRepository), "/chat/{appointmentId}/{userId}")
                .addInterceptors(new ConnectionInterceptor(appointmentRepository))
                .setAllowedOrigins("*");
    }
}
