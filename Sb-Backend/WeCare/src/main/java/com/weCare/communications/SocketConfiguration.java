package com.weCare.communications;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistration;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.weCare.repository.AppointmentRepository;
import com.weCare.repository.MessageRepository;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

@Configuration
@EnableWebSocket
public class SocketConfiguration implements WebSocketConfigurer {

	@Autowired
	private AppointmentRepository appointmentRepository;

	@Autowired
	private MessageRepository messageRepository;

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(
				new MessageWebSocketHandler(appointmentRepository, messageRepository),
				"/chat/{appointmentId}/{userId}")
		.addInterceptors(new ConnectionInterceptor(appointmentRepository))
				.setAllowedOrigins("*");
	}
}
