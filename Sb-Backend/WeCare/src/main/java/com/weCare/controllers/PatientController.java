package com.weCare.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.weCare.modals.Patient;
import com.weCare.services.PatientService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/patients")
public class PatientController {

	@Autowired
	private PatientService patientService;

	@PostMapping()
	public ResponseEntity<Patient> savePatient(@Valid @RequestBody Patient patient) {
		return new ResponseEntity<Patient>(patientService.savePatient(patient), HttpStatus.ACCEPTED);
	}

	@PostMapping(value = "/save_all_patients")
	public ResponseEntity<List<Patient>> savePatients(@Valid @RequestBody List<Patient> patients) {
		return new ResponseEntity<List<Patient>>(patientService.savePatients(patients), HttpStatus.ACCEPTED);
	}

	@GetMapping(value = "/{patient_id}")
	public ResponseEntity<Patient> getPatientById(@PathVariable("patient_id") String patient_id) {
		return new ResponseEntity<Patient>(patientService.getPatientById(patient_id), HttpStatus.ACCEPTED);
	}

	@GetMapping(value = "/by_name/{patient_name}")
	public ResponseEntity<List<Patient>> getPatientByNamePattern(@PathVariable("patient_name") String patient_name) {
		return new ResponseEntity<List<Patient>>(patientService.getPatientByNamePattern(patient_name),
				HttpStatus.ACCEPTED);
	}

	@GetMapping()
	public ResponseEntity<List<Patient>> getAllPatients() {
		return new ResponseEntity<List<Patient>>(patientService.getAllPatients(), HttpStatus.ACCEPTED);
	}

	@PutMapping("/{patient_id}")
	public ResponseEntity<Patient> updatePatient(@PathVariable("patient_id") String patient_id,
			@RequestBody Patient patient) {
		return new ResponseEntity<Patient>(patientService.updatePatient(patient_id, patient), HttpStatus.ACCEPTED);
	}

	@DeleteMapping(value = "/{patient_id}")
	public ResponseEntity<String> deletePatientById(@PathVariable("patient_id") String patient_id) {
		return new ResponseEntity<String>(patientService.deletePatientById(patient_id), HttpStatus.ACCEPTED);
	}
	
//	@PostMapping(value = "/basicLogin")
//	public ResponseEntity<String> loginWithBasicAuthentication(Authentication authentication){
//		
//		String name = authentication.getName();
//		
//		return new ResponseEntity<String>(name+" Logged-In", HttpStatus.ACCEPTED);
//	}
}
