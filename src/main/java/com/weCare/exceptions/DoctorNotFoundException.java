package com.weCare.exceptions;

public class DoctorNotFoundException extends RuntimeException {
	
	public DoctorNotFoundException(String msg) {
		super(msg);
	}
	
	public DoctorNotFoundException() {
		super("Doctor not found!!!");
	}

}
