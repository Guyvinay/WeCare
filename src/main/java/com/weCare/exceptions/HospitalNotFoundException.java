package com.weCare.exceptions;

public class HospitalNotFoundException extends RuntimeException {

	public HospitalNotFoundException(String msg) {
		super(msg);
	}
	
	public HospitalNotFoundException() {
		super("Hospital not found!!!");
	}
}
