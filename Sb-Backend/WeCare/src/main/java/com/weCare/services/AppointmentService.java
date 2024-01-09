package com.weCare.services;

import com.weCare.modals.Appointment;

import java.util.List;

public interface AppointmentService {

    public Appointment bookAppointment(
            Appointment appointment,
            String hospital_id, String patient_id
    );
    
    public Appointment bookAppointmentWithDoctor(
            Appointment appointment,
            String hospital_id, String patient_id, String doctor_id
    );
    public Appointment getAppointmentById(String appointment_id);

    public List<Appointment> getAllAppointments();

    public Appointment updateAppointment(String appointment_id, Appointment appointment);

    public String deleteAppointmentById(String appointment_id);
    
}
