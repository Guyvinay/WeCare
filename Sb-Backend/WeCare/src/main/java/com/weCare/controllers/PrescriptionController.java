package com.weCare.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.weCare.modals.Prescription;
import com.weCare.services.PrescriptionService;

/*

{
  "prescription_medications": [
    "stay right there i am coming","he has taken the time stone","nothing can be done"
  ],
  "additional_instructions": "do not mess with time i don't have time stone"
}

 */

@RestController
@RequestMapping(value = "/prescriptions")
public class PrescriptionController {
	
	@Autowired
	private PrescriptionService prescriptionService;

	@PostMapping(value = "/{appointment_id}")
	public ResponseEntity<Prescription> bookAppointment(
			@PathVariable("appointment_id")String appointment_id,
			@RequestBody Prescription prescription
	){

		return new ResponseEntity<Prescription>(
				prescriptionService.generatePrescription(appointment_id,prescription),
				HttpStatus.ACCEPTED
		);
	}
	
}
