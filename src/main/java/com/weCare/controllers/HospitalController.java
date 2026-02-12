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

import com.weCare.modals.Hospital;
import com.weCare.services.HospitalService;

@RestController
@RequestMapping(value = "/hospitals")
public class HospitalController {

	@Autowired
	private HospitalService hospitalService;

	@PostMapping()
	public ResponseEntity<Hospital> saveHospital(@RequestBody Hospital hospital) {
		return new ResponseEntity<Hospital>(hospitalService.saveHospital(hospital), HttpStatus.ACCEPTED);
	}

	@PostMapping(value = "/save_all_hospitals")
	public ResponseEntity<List<Hospital>> saveHospitals(@RequestBody List<Hospital> hospitals) {
		return new ResponseEntity<List<Hospital>>(hospitalService.saveHospitals(hospitals), HttpStatus.ACCEPTED);
	}

	@GetMapping(value = "/{hospital_id}")
	public ResponseEntity<Hospital> getHospitalById(@PathVariable("hospital_id") String hospital_id) {
		return new ResponseEntity<Hospital>(hospitalService.getHospitalById(hospital_id), HttpStatus.ACCEPTED);
	}

	@GetMapping()
	public ResponseEntity<List<Hospital>> getAllHospitals() {
		return new ResponseEntity<List<Hospital>>(hospitalService.getAllHospitals(), HttpStatus.ACCEPTED);
	}

	@PutMapping("/{hospital_id}")
	public ResponseEntity<Hospital> updateHospital(@PathVariable("hospital_id") String hospital_id,
			@RequestBody Hospital hospital) {
		return new ResponseEntity<Hospital>(hospitalService.updateHospital(hospital_id, hospital), HttpStatus.ACCEPTED);
	}

	@DeleteMapping(value = "/{hospital_id}")
	public ResponseEntity<String> deleteHospitalById(@PathVariable("hospital_id") String hospital_id) {
		return new ResponseEntity<String>(hospitalService.deleteHospitalById(hospital_id), HttpStatus.ACCEPTED);
	}
}
