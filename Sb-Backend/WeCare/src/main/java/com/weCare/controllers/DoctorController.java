package com.weCare.controllers;

import java.util.List;

import jakarta.validation.Valid;
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

import com.weCare.modals.Doctor;
import com.weCare.services.DoctorService;

/*
{
"email":"docone@gmail.com",
"userName":"doc-one",
"passWord":"string",
"profile_picture":"profile-pic",
"role":"DOCTOR",
"doctor_name":"DOC One",
"department":"ENT",
"qualification":"LLB Ha Ha",
"mobile":"1234567890"
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
    @GetMapping(value = "/{doctor_id}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable("doctor_id") String doctor_id){
        return new ResponseEntity<Doctor>(
                doctorService.getDoctorById(doctor_id),
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
    @DeleteMapping(value = "/{doctor_id}")
    public ResponseEntity<String> deleteDoctorById(@PathVariable("doctor_id") String doctor_id){
        return new ResponseEntity<String>(
                doctorService.deleteDoctorById(doctor_id),
                HttpStatus.ACCEPTED
        );
    }
}
