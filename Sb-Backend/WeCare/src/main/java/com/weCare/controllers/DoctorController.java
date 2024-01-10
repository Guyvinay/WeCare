package com.weCare.controllers;

import java.util.List;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.weCare.modals.Doctor;
import com.weCare.services.DoctorService;

/*
{
  "email": "stefen@gmail.com",
  "userName": "stefenn",
  "passWord": "string",
  "profile_picture": "Dr-Stefen-Strange",
  "role": "DOCTOR",
  "doctor_name": "Dr. Stefen Strange",
  "department": "ENT",
  "qualification": "Masters-in-teleportation-hipnotism-time-manipulation-foresee",
  "mobile": "8765432123",
  "address": {
    "locality": "Bleeker Street",
    "city": "LA",
    "zip_code": 200006,
    "state": "Los Angeles",
    "country": "USA"
  }
}
 */

@RestController
@RequestMapping(value = "/doctors")
public class DoctorController {
    
    @Autowired
    private DoctorService doctorService;

    @PostMapping()
    public ResponseEntity<Doctor> saveDoctor( @Valid @RequestBody Doctor doctor){
        return new ResponseEntity<Doctor>(
                doctorService.saveDoctor(doctor),
                HttpStatus.ACCEPTED
        );
    }
    @PostMapping(value = "/{hospital_id}")
    public ResponseEntity<Doctor> saveDoctor(@Valid @RequestBody Doctor doctor,
    		                 @PathVariable("hospital_id") String hospital_id
    		){
        return new ResponseEntity<Doctor>(
                doctorService.saveDoctorWithHospital(doctor, hospital_id),
                HttpStatus.ACCEPTED
        );
    }
    @GetMapping(value = "/{doctor_id}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable("doctor_id") String doctor_id){
        return new ResponseEntity<Doctor>(
                doctorService.getDoctorById(doctor_id),
                HttpStatus.ACCEPTED
        );
    }
    
    @GetMapping(value = "/by_department/{doctor_department}")
    public ResponseEntity<List<Doctor>> getDoctorByDepartmentPattern(@PathVariable("doctor_department") String doctor_department){
        return new ResponseEntity<List<Doctor>>(
                doctorService.getDoctorByDepartmentPattern(doctor_department),
                HttpStatus.ACCEPTED
        );
    }
    
    @GetMapping(value = "/by_name/{doctor_name}")
    public ResponseEntity<List<Doctor>> getDoctorByNamePattern(@PathVariable("doctor_name") String doctor_name){
        return new ResponseEntity<List<Doctor>>(
                doctorService.getDoctorByNamePattern(doctor_name),
                HttpStatus.ACCEPTED
        );
    }
    
    @GetMapping()
    public ResponseEntity<List<Doctor>> getAllDoctors(){
        return new ResponseEntity<List<Doctor>>(
                doctorService.getAllDoctors(),
                HttpStatus.ACCEPTED
        );
    }
    @PutMapping("/{doctor_id}")
    public ResponseEntity<Doctor> updateDoctor( @PathVariable("doctor_id") String doctor_id,
                                                  @RequestBody Doctor doctor){
        return new ResponseEntity<Doctor>(
                doctorService.updateDoctor(doctor_id, doctor),
                HttpStatus.ACCEPTED
        );
    }

    @PatchMapping("/{doctor_id}/{hospital_id}")
    public ResponseEntity<Doctor> updateDoctorHospital( @PathVariable("doctor_id") String doctor_id,
                                                @PathVariable("hospital_id") String hospital_id){
        return new ResponseEntity<Doctor>(
                doctorService.updateDoctorHospital(doctor_id, hospital_id),
                HttpStatus.ACCEPTED
        );
    }

    @DeleteMapping(value = "/{doctor_id}")
    public ResponseEntity<String> deleteDoctorById(@PathVariable("doctor_id") String doctor_id){
        return new ResponseEntity<String>(
                doctorService.deleteDoctorById(doctor_id),
                HttpStatus.ACCEPTED
        );
    }
}
