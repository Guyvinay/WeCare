package com.weCare.exceptions;

public class PatientNotFoundException extends RuntimeException {

	public PatientNotFoundException(String msg) {
		super(msg);
	}
	public PatientNotFoundException() {
		super("Patient Not Found!!!");
	}
	
}
