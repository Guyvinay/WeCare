package com.weCare.controllers;

import java.util.List;

import com.weCare.dto.AppointmentResponseDTO;
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

import com.weCare.modals.Appointment;
import com.weCare.services.AppointmentService;

@RestController
@RequestMapping(value = "/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping(value = "/{hospital_id}/{patient_id}")
    public ResponseEntity<AppointmentResponseDTO> bookAppointment(
                                @PathVariable("patient_id")String patient_id,
                                @PathVariable("hospital_id")String hospital_id,
                                @RequestBody Appointment appointment
                                        ){

        return new ResponseEntity<AppointmentResponseDTO>(
                appointmentService.bookAppointment(appointment, hospital_id, patient_id),
                HttpStatus.ACCEPTED
        );
    }
    
    @PostMapping(value = "/{hospital_id}/{patient_id}/{doctor_id}")
    public ResponseEntity<Appointment> bookAppointmentWithDoctor(
                                @PathVariable("patient_id")String patient_id,
                                @PathVariable("doctor_id")String doctor_id,
                                @PathVariable("hospital_id")String hospital_id,
                                @RequestBody Appointment appointment
                                        ){

        return new ResponseEntity<Appointment>(
                appointmentService.bookAppointmentWithDoctor(appointment, hospital_id, patient_id,doctor_id),
                HttpStatus.ACCEPTED
        );
    }
    
    @GetMapping(value = "/{appointment_id}")
    public ResponseEntity<AppointmentResponseDTO> getAppointmentById(
                      @PathVariable("appointment_id") String appointment_id){

        return new ResponseEntity<AppointmentResponseDTO>(
                appointmentService.getAppointmentById(appointment_id),
                HttpStatus.ACCEPTED
        );
    }
    
    @GetMapping()
    public ResponseEntity<List<AppointmentResponseDTO>> getAllAppointments(){
        return new ResponseEntity<List<AppointmentResponseDTO>>(
                appointmentService.getAllAppointments(),
                HttpStatus.ACCEPTED
        );
    }
    
    @PutMapping(value = "/{appointment_id}")
    public ResponseEntity<AppointmentResponseDTO> updateAppointment( @PathVariable("appointment_id") String appointment_id,
                      @RequestBody Appointment appointment){
        return new ResponseEntity<AppointmentResponseDTO>(
                appointmentService.updateAppointment(appointment_id, appointment),
                HttpStatus.ACCEPTED
        );
    }
    
    @DeleteMapping(value = "/{appointment_id}")
    public ResponseEntity<String> deleteAppointmentById(
    		@PathVariable("appointment_id") String appointment_id){
        return new ResponseEntity<String>(
                appointmentService.deleteAppointmentById(appointment_id),
                HttpStatus.ACCEPTED
        );
    }
    
}
