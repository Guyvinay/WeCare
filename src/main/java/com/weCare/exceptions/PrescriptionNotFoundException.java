package com.weCare.exceptions;

public class PrescriptionNotFoundException extends RuntimeException {

    public PrescriptionNotFoundException(String msg){
        super(msg);
    }
    public PrescriptionNotFoundException(){
        super("Prescription not found!!!");
    }
}
