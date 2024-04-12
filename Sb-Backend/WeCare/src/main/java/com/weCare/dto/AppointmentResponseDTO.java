package com.weCare.dto;

import com.weCare.modals.AppointmentStatus;
import com.weCare.modals.Department;
import com.weCare.modals.SlotPeriod;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AppointmentResponseDTO {

	private String appointment_id;

	private LocalDateTime booking_time;

	private LocalDate appointment_date;

	private String appointmentStarts;

	private String appointmentEnds;

	private Department department;

	private String ailment_description;

	private AppointmentStatus status;

	private SlotPeriod slot;

	private DoctorDTO doctor;

	private PatientDTO patient;

	private HospitalDTO hospital;


}
