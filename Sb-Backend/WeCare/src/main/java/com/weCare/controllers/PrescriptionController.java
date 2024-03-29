package com.weCare.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.weCare.modals.Prescription;
import com.weCare.services.PrescriptionService;

@RestController
@RequestMapping(value = "/prescriptions")
public class PrescriptionController {

	@Autowired
	private PrescriptionService prescriptionService;

	@PostMapping(value = "/{appointment_id}")
	public ResponseEntity<Prescription> bookAppointment(@PathVariable("appointment_id") String appointment_id,
			@RequestBody Prescription prescription) {

		return new ResponseEntity<Prescription>(prescriptionService.generatePrescription(appointment_id, prescription),
				HttpStatus.ACCEPTED);
	}

	@GetMapping(value = "/{prescription_id}")
	public ResponseEntity<Prescription> getPrescriptionById(@PathVariable("prescription_id") String prescription_id) {
		return new ResponseEntity<Prescription>(prescriptionService.getPrescriptionById(prescription_id),
				HttpStatus.ACCEPTED);
	}

	@GetMapping()
	public ResponseEntity<List<Prescription>> getAllPrescriptions() {
		return new ResponseEntity<List<Prescription>>(prescriptionService.getAllPrescriptions(), HttpStatus.ACCEPTED);
	}

	@PutMapping(value = "/{prescription_id}")
	public ResponseEntity<Prescription> updatePrescription(@PathVariable("prescription_id") String prescription_id,
			@RequestBody Prescription prescription) {

		return new ResponseEntity<Prescription>(prescriptionService.updatePrescription(prescription_id, prescription),
				HttpStatus.ACCEPTED);
	}

	@DeleteMapping(value = "/{prescription_id}")
	public ResponseEntity<String> deletePrescriptionById(@PathVariable("prescription_id") String prescription_id) {
		return new ResponseEntity<String>(prescriptionService.deletePrescriptionById(prescription_id),
				HttpStatus.ACCEPTED);
	}

}
