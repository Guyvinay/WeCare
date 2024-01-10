package com.weCare.services;

import java.util.List;

import com.weCare.modals.Prescription;

public interface PrescriptionService {

    public Prescription generatePrescription (String appointment_id, Prescription prescription);

    public Prescription getPrescriptionById(String prescription_id);

    public List<Prescription> getAllPrescriptions();

    public Prescription updatePrescription(String prescription_id, Prescription prescription);

    public String deletePrescriptionById(String prescription_id);
    
}
