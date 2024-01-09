package com.weCare.controllers;

import com.weCare.modals.Appointment;
import com.weCare.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping(value = "/{hospital_id}/{patient_id}")
    public ResponseEntity<Appointment> bookAppointment(
                                            @PathVariable("patient_id")String patient_id,
                                            @PathVariable("hospital_id")String hospital_id,
                                            @RequestBody Appointment appointment
                                        ){

        return new ResponseEntity<Appointment>(
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
    public ResponseEntity<Appointment> getAppointmentById(
                      @PathVariable("appointment_id") String appointment_id){

        return new ResponseEntity<Appointment>(
                appointmentService.getAppointmentById(appointment_id),
                HttpStatus.ACCEPTED
        );
    }
    
    @GetMapping()
    public ResponseEntity<List<Appointment>> getAllAppointments(){
        return new ResponseEntity<List<Appointment>>(
                appointmentService.getAllAppointments(),
                HttpStatus.ACCEPTED
        );
    }
    
    @PutMapping(value = "/{appointment_id}")
    public ResponseEntity<Appointment> updateAppointment( @PathVariable("appointment_id") String appointment_id,
                                                  @RequestBody Appointment appointment){
        return new ResponseEntity<Appointment>(
                appointmentService.updateAppointment(appointment_id, appointment),
                HttpStatus.ACCEPTED
        );
    }
    
    @DeleteMapping(value = "/{appointment_id}")
    public ResponseEntity<String> deleteAppointmentById(@PathVariable("appointment_id") String appointment_id){
        return new ResponseEntity<String>(
                appointmentService.deleteAppointmentById(appointment_id),
                HttpStatus.ACCEPTED
        );
    }
    
}
