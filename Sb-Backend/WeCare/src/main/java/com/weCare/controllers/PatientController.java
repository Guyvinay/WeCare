package com.weCare.controllers;

import com.weCare.modals.Patient;
import com.weCare.services.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
{
  "email": "patient_demo_one@gmail.com",
  "userName": "patient_demo_one",
  "passWord": "string",
  "profile_picture": "patient_demo_one",
  "role": "PATIENT",
  "patient_name": "patient_demo_one",
  "patient_gender": "MALE",
  "date_of_birth": "2001-01-10",
  "mobile": "7654321234",
  "address": {
    "locality": "NIT Patna",
    "city": "Patna",
    "zip_code": 800005,
    "state": "Bihar",
    "country": "India"
  }
}
*/

@RestController
@RequestMapping(value = "/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @PostMapping()
    public ResponseEntity<Patient> savePatient(@Valid @RequestBody Patient patient){
        return new ResponseEntity<Patient>(
                patientService.savePatient(patient),
                HttpStatus.ACCEPTED
        );
    }
    @GetMapping(value = "/{patient_id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable("patient_id") String patient_id){
        return new ResponseEntity<Patient>(
                patientService.getPatientById(patient_id),
                HttpStatus.ACCEPTED
        );
    }
    @GetMapping()
    public ResponseEntity<List<Patient>> getAllPatients(){
        return new ResponseEntity<List<Patient>>(
                patientService.getAllPatients(),
                HttpStatus.ACCEPTED
        );
    }
    @PutMapping("/{patient_id}")
    public ResponseEntity<Patient> updatePatient( @PathVariable("patient_id") String patient_id,
                                                @RequestBody Patient patient){
        return new ResponseEntity<Patient>(
                patientService.updatePatient(patient_id, patient),
                HttpStatus.ACCEPTED
        );
    }
    @DeleteMapping(value = "/{patient_id}")
    public ResponseEntity<String> deletePatientById(@PathVariable("patient_id") String patient_id){
        return new ResponseEntity<String>(
                patientService.deletePatientById(patient_id),
                HttpStatus.ACCEPTED
        );
    }
}
