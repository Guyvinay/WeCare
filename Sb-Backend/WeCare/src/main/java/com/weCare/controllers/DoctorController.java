package com.weCare.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.weCare.modals.Doctor;
import com.weCare.services.DoctorService;

import jakarta.validation.Valid;


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
    
    @PostMapping(value = "/save_all_doctors/{hospital_id}")
    public ResponseEntity<List<Doctor>> saveDoctors(
    		@PathVariable("hospital_id")String hospital_id,
    		@Valid @RequestBody List<Doctor> doctors){
        return new ResponseEntity<List<Doctor>>(
                doctorService.saveDoctors(doctors, hospital_id),
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
    public ResponseEntity<Doctor> updateDoctor(
    		@PathVariable("doctor_id") String doctor_id,
            @RequestBody Doctor doctor){
        return new ResponseEntity<Doctor>(
                doctorService.updateDoctor(doctor_id, doctor),
                HttpStatus.ACCEPTED
        );
    }

    @PatchMapping("/{doctor_id}/{hospital_id}")
    public ResponseEntity<Doctor> updateDoctorHospital( 
    		@PathVariable("doctor_id") String doctor_id,
            @PathVariable("hospital_id") String hospital_id){
        return new ResponseEntity<Doctor>(
                doctorService.updateDoctorHospital(doctor_id, hospital_id),
                HttpStatus.ACCEPTED
        );
    }

    @DeleteMapping(value = "/{doctor_id}")
    public ResponseEntity<String> deleteDoctorById(
    		@PathVariable("doctor_id") String doctor_id){
        return new ResponseEntity<String>(
                doctorService.deleteDoctorById(doctor_id),
                HttpStatus.ACCEPTED
        );
    }
    
    @PostMapping(value = "/basicLogin")
	public ResponseEntity<String> loginWithBasicAuthentication(Authentication authentication){
		
		String name = authentication.getName();
		
		return new ResponseEntity<String>(name+" Logged-In", HttpStatus.ACCEPTED);
	}
    
}
