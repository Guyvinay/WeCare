package com.weCare.controllers;

import com.weCare.modals.Hospital;
import com.weCare.services.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 {
  "hospital_name": "AIIMS Patna",
  "contact": "9876543211",
  "description": "This is AIIMS Hospital Patna",
  "address": {
    "locality": "Bazar Samiti",
    "city": "Patna",
    "zip_code": 800006,
    "state": "Bihar",
    "country": "India"
  }
}
 */

@RestController
@RequestMapping(value = "/hospitals")
public class HospitalController {
    @Autowired
    private HospitalService hospitalService;

    @PostMapping()
    public ResponseEntity<Hospital> saveHospital(@RequestBody Hospital hospital){
        return new ResponseEntity<Hospital>(
                hospitalService.saveHospital(hospital),
                HttpStatus.ACCEPTED
        );
    }
    @GetMapping(value = "/{hospital_id}")
    public ResponseEntity<Hospital> getHospitalById(@PathVariable("hospital_id") String hospital_id){
        return new ResponseEntity<Hospital>(
                hospitalService.getHospitalById(hospital_id),
                HttpStatus.ACCEPTED
        );
    }
    @GetMapping()
    public ResponseEntity<List<Hospital>> getAllHospitals(){
        return new ResponseEntity<List<Hospital>>(
                hospitalService.getAllHospitals(),
                HttpStatus.ACCEPTED
        );
    }
    @PutMapping("/{hospital_id}")
    public ResponseEntity<Hospital> updateHospital( @PathVariable("hospital_id") String hospital_id,
                                                  @RequestBody Hospital hospital){
        return new ResponseEntity<Hospital>(
                hospitalService.updateHospital(hospital_id, hospital),
                HttpStatus.ACCEPTED
        );
    }
    @DeleteMapping(value = "/{hospital_id}")
    public ResponseEntity<String> deleteHospitalById(@PathVariable("hospital_id") String hospital_id){
        return new ResponseEntity<String>(
                hospitalService.deleteHospitalById(hospital_id),
                HttpStatus.ACCEPTED
        );
    }
}
