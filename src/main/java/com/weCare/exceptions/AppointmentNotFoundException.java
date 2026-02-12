package com.weCare.exceptions;

public class AppointmentNotFoundException extends RuntimeException {

	public AppointmentNotFoundException(String msg) {
		super(msg);
	}
	
	public AppointmentNotFoundException() {
		super("Appointment not found!!!");
	}
}
