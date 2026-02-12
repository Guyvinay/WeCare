package com.weCare.exceptions;

public class CredsWentWrongException extends RuntimeException{

	public CredsWentWrongException(String msg) {
		super(msg);
	}
	public CredsWentWrongException() {
		super("Wrong Credentials Found!!!");
	}
}
