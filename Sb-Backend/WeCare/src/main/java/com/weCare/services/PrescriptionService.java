package com.weCare.services;

import com.weCare.modals.Prescription;

import java.util.List;

public interface PrescriptionService {

    public Prescription generatePrescription(Prescription prescription);

    public Prescription getPrescriptionById(String prescription_id);

    public List<Prescription> getAllPrescriptions();

    public Prescription updatePrescription(String prescription_id, Prescription prescription);

    public String deletePrescriptionById(String prescription_id);
    
}
