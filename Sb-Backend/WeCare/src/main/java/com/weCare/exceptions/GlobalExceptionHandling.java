package com.weCare.exceptions;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

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
	
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<ExceptionDefinition> noHandlerFoundException(NoHandlerFoundException ex, WebRequest wb){
		return new ResponseEntity<ExceptionDefinition>(
				new ExceptionDefinition(
						LocalDateTime.now(),
						"There is no handler for this endpoint: " + wb.getDescription(false),
						ex.getMessage()
				),
				HttpStatus.BAD_REQUEST
		);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ExceptionDefinition> methodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest wb){
		
		
		List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();
		List<String>      errorMessages = MethodArgumentNotValidException.errorsToStringList(allErrors);
		
		return new ResponseEntity<ExceptionDefinition>(
				new ExceptionDefinition(
						LocalDateTime.now(),
						String.join(", ", errorMessages),
						wb.getDescription(false)
				),
				HttpStatus.BAD_REQUEST
		);
	}
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<ExceptionDefinition> duplicateExceptionHandler(DataIntegrityViolationException ex, WebRequest wb){

		org.hibernate.exception.ConstraintViolationException cause =
              (org.hibernate.exception.ConstraintViolationException) ex.getCause();
		
		String errMessage = cause.getSQLException().getMessage();
					
		return new ResponseEntity<ExceptionDefinition>(
				 new ExceptionDefinition(
							LocalDateTime.now(),
							errMessage,
							wb.getDescription(false)
							),
				HttpStatus.BAD_REQUEST
		);
	}
	
	
	
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ExceptionDefinition> notFoundExceptionHandler(NotFoundException ex, WebRequest wb){
		return new ResponseEntity<ExceptionDefinition>(
				new ExceptionDefinition(
						LocalDateTime.now(),
						ex.getMessage(),
						wb.getDescription(false)
				),
				HttpStatus.BAD_REQUEST
		);
	}
	
	@ExceptionHandler(DoctorNotFoundException.class)
	public ResponseEntity<ExceptionDefinition> doctorNotFoundExceptionHandler(DoctorNotFoundException ex, WebRequest wb){
		return new ResponseEntity<ExceptionDefinition>(
				new ExceptionDefinition(
						LocalDateTime.now(),
						ex.getMessage(),
						wb.getDescription(false)
				),
				HttpStatus.BAD_REQUEST
		);
	}
	
	@ExceptionHandler(PatientNotFoundException.class)
	public ResponseEntity<ExceptionDefinition> patientNotFoundExceptionHandler(PatientNotFoundException ex, WebRequest wb){
		return new ResponseEntity<ExceptionDefinition>(
				new ExceptionDefinition(
						LocalDateTime.now(),
						ex.getMessage(),
						wb.getDescription(false)
				),
				HttpStatus.BAD_REQUEST
		);
	}
	
	@ExceptionHandler(HospitalNotFoundException.class)
	public ResponseEntity<ExceptionDefinition> hospitalNotFoundExceptionHandler(HospitalNotFoundException ex, WebRequest wb){
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
