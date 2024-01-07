package com.weCare.services;

import com.weCare.modals.Patient;

import java.util.List;

public interface PatientService {
	
	public Patient savePatient(Patient patient);

    public Patient getPatientById(String patient_id);

    public List<Patient> getAllPatients();

    public Patient updatePatient(String patient_id, Patient patient);

    public String deletePatientById(String patient_id);



}
