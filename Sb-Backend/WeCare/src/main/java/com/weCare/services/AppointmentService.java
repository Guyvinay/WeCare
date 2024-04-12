package com.weCare.services;

import java.util.List;

import com.weCare.dto.AppointmentResponseDTO;
import com.weCare.modals.Appointment;

public interface AppointmentService {

	public AppointmentResponseDTO bookAppointment(Appointment appointment, String hospital_id, String patient_id);

	public Appointment bookAppointmentWithDoctor(Appointment appointment, String hospital_id, String patient_id,
			String doctor_id);

	public AppointmentResponseDTO getAppointmentById(String appointment_id);

	public List<AppointmentResponseDTO> getAllAppointments();

	public AppointmentResponseDTO updateAppointment(String appointment_id, Appointment appointment);

	public String deleteAppointmentById(String appointment_id);

}
