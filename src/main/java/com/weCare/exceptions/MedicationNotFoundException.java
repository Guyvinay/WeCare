package com.weCare.exceptions;

public class MedicationNotFoundException extends  RuntimeException {
    public MedicationNotFoundException(String msg){
        super(msg);
    }
    public MedicationNotFoundException(){
        super("Medication not found!!!");
    }
}
