package com.weCare.servicesImpls;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.weCare.exceptions.MessageNotFoundException;
import com.weCare.modals.Message;
import com.weCare.repository.MessageRepository;
import com.weCare.services.MessageService;

@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageRepository messageRepository;
	
	@Override
	public List<Message> getAllMessagesByPagination(
			int page_no, int limit, String direction, String field) {

		Pageable  pageRequest = PageRequest.of(page_no, limit, Sort.by(direction.equals("ASC")?Sort.Direction.ASC:Sort.Direction.DESC, field));
		
//		List<Message> list = messageRepository.findAll(Sort.by(Sort.Direction.DESC, "timeStamp"));
		
		Page<Message> pages = messageRepository.findAll(pageRequest);
		
		List<Message> all_messages = pages.getContent();
		if(all_messages.isEmpty())
			throw new MessageNotFoundException("No Messages found!!!");
		
		return all_messages;
	}
	
	@Override
	public List<Message> getAllMessages() {
		List<Message> all_messages = messageRepository.findAll();
		if(all_messages.isEmpty())
			throw new MessageNotFoundException("No Messages found!!!");
		return all_messages;
	}

	@Override
	public List<Message> getAllMessagesByAppointmentId(String appointment_id) {
		
		return null;
	}

	@Override
	public Message getMessageById(String message_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteMessageById(String message_id) {
		// TODO Auto-generated method stub
		return null;
	}

}
