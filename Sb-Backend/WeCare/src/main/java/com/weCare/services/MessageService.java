package com.weCare.services;

import java.util.List;

import com.weCare.modals.Message;

public interface MessageService {

	public List<Message> getAllMessagesByPagination(
			int page, int limit, String direction, String field
			);
	public List<Message> getAllMessages();
	public List<Message> getAllMessagesByAppointmentId(String appointment_id);
	
	public Message getMessageById(String message_id);
	
	public String deleteMessageById(String message_id);
}
