package com.weCare.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandling {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ExceptionDefinition> globalExceptionHandle(Exception ex, WebRequest wb){
		return new ResponseEntity<ExceptionDefinition>(
				new ExceptionDefinition(
						LocalDateTime.now(),
						ex.getMessage(),
						wb.getDescription(false)
				),
				HttpStatus.BAD_REQUEST
		);
	}
	
}
