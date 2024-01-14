package com.weCare.controllers;

import com.weCare.modals.Medication;
import com.weCare.services.MedicationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/medications")
public class MedicationController {

    @Autowired
    private MedicationService medicationService;

    @PostMapping()
    public ResponseEntity<Medication> saveMedication(@Valid @RequestBody Medication medication){

        return new ResponseEntity<Medication>(
                medicationService.saveMedication(medication),
                HttpStatus.ACCEPTED
        );
    }
    
    @PostMapping(value = "/save_all")
    public ResponseEntity<List<Medication>> saveAllMedications(@Valid @RequestBody List<Medication> medications){

        return new ResponseEntity<List<Medication>>(
                medicationService.saveMedications(medications),
                HttpStatus.ACCEPTED
        );
    }

    @GetMapping(value = "/{medication_id}")
    public ResponseEntity<Medication> getMedicationById(
            @PathVariable("medication_id") String medication_id){

        return new ResponseEntity<Medication>(
                medicationService.getMedicationById(medication_id),
                HttpStatus.ACCEPTED
        );
    }

    @GetMapping()
    public ResponseEntity<List<Medication>> getAllMedications(){

        return new ResponseEntity<List<Medication>>(
                medicationService.getAllMedications(),
                HttpStatus.ACCEPTED
        );
    }

    @PutMapping(value = "/{medication_id}")
    public ResponseEntity<Medication> updateMedication(
            @PathVariable("medication_id") String medication_id, 
            @RequestBody Medication medication){

        return new ResponseEntity<Medication>(
                medicationService.updateMedication(medication_id, medication),
                HttpStatus.ACCEPTED
        );
    }

    @DeleteMapping(value = "/{medication_id}")
    public ResponseEntity<String> deleteMedicationById(
            @PathVariable("medication_id")String medication_id){

        return new ResponseEntity<String>(
                medicationService.deleteMedicationById(medication_id),
                HttpStatus.ACCEPTED
        );
    }

}
