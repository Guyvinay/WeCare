package com.weCare.services;

import java.util.List;

import com.weCare.modals.Patient;

public interface PatientService {
	
	public Patient savePatient(Patient patient);

    public Patient getPatientById(String patient_id);
    
    public List<Patient> getPatientByNamePattern(String patient_name);

    public List<Patient> getAllPatients();

    public Patient updatePatient(String patient_id, Patient patient);

    public String deletePatientById(String patient_id);



}
