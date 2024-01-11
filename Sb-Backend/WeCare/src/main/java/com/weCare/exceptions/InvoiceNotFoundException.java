package com.weCare.exceptions;

public class InvoiceNotFoundException extends RuntimeException {

	public InvoiceNotFoundException() {
		// TODO Auto-generated constructor stub
		super("Invoice Not Found!!!");
	}

	public InvoiceNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
}
