package com.weCare.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.weCare.modals.Message;
import com.weCare.services.MessageService;

@RestController
@RequestMapping(value = "/messages")
public class MessageController {

	@Autowired
	private MessageService messageService;

	@GetMapping(value = "/paged_messages")
	public ResponseEntity<List<Message>> getAllMessagesByPagination(
		@RequestParam(required=false,defaultValue="0",name="page")Integer page,
		@RequestParam(required=false,defaultValue="10",name="limit") Integer limit,
		@RequestParam(required=false,defaultValue="DESC",name= "direction")String direction,
		@RequestParam(required=false,defaultValue="timeStamp",name="field")String field
			){
		System.out.println(page);
		System.out.println(limit);
		System.out.println(direction);
		System.out.println(field);
		return  new ResponseEntity<List<Message>>(
				messageService.getAllMessagesByPagination(page,limit,direction,field), 
				HttpStatus.ACCEPTED);
	}
	
	@GetMapping
	public ResponseEntity<List<Message>> getAllMessages(){

		return  new ResponseEntity<List<Message>>(
				messageService.getAllMessages(), 
				HttpStatus.ACCEPTED);
	}
	
}
