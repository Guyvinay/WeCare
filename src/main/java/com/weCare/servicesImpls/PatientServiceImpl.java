package com.weCare.servicesImpls;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.weCare.exceptions.NotFoundException;
import com.weCare.exceptions.PatientNotFoundException;
import com.weCare.modals.Address;
import com.weCare.modals.Patient;
import com.weCare.repository.AddressRepository;
import com.weCare.repository.PatientRepository;
import com.weCare.services.PatientService;

@Service
public class PatientServiceImpl implements PatientService {

	@Autowired
	private PatientRepository patientRepository;

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public Patient savePatient(Patient patient) {

		Address address = addressRepository.save(patient.getAddress());
		patient.setAddress(address);
		
		patient.setPassWord(passwordEncoder.encode(patient.getPassWord()));

		return patientRepository.save(patient);
	}

	@Override
	public List<Patient> savePatients(List<Patient> patients) {

		if (patients.isEmpty())
			throw new PatientNotFoundException("Patients not found to persist!!!");

		for (Patient patient : patients) {
			patient.setAddress(addressRepository.save(patient.getAddress()));
			patient.setPassWord(passwordEncoder.encode(patient.getPassWord()));
		}

		return patientRepository.saveAll(patients);

	}

	@Override
	public Patient getPatientById(String patient_id) {

		return patientRepository.findById(patient_id)
				.orElseThrow(() -> new NotFoundException("Hospital with id: " + patient_id + ", not found!!!"));
	}

	@Override
	public List<Patient> getPatientByNamePattern(String patient_name) {
		List<Patient> patients = patientRepository.findByNamePattern("%" + patient_name + "%");
		return patients;
	}

	@Override
	public List<Patient> getAllPatients() {
		List<Patient> patients = patientRepository.findAll();
		if (patients.isEmpty())
			throw new NotFoundException("No Patients Found!!!");
		return patients;
	}

	@Override
	public Patient updatePatient(String patient_id, Patient patient) {
		return null;
	}

	@Override
	public String deletePatientById(String patient_id) {
		Patient patient = patientRepository.findById(patient_id)
				.orElseThrow(() -> new NotFoundException("Hospital with id: " + patient_id + ", not found!!!"));
		patientRepository.delete(patient);
		return "Patient with id: " + patient_id + ", deleted successfully";
	}

}
