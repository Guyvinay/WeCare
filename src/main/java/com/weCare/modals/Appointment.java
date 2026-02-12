package com.weCare.modals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "appointments")
public class Appointment {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String appointment_id;

	private LocalDateTime booking_time;

	private LocalDate appointment_date;
	
	private String appointmentStarts;
	
	private String appointmentEnds;

	@Column(nullable = false)
	@NotNull(message = "Department cannot be blank!!!")
	@Enumerated(EnumType.STRING)
	private Department department;

	@NotBlank(message = "Provide details about ailments!!!")
	private String ailment_description;

	@Enumerated(EnumType.STRING)
	private AppointmentStatus status;
	
//	@Transient
	@Column(nullable = false)
	@NotNull(message = "Slot Cannot be Null!!!")
	@Enumerated(EnumType.STRING)
	private SlotPeriod slot;

	@ManyToOne
	@JoinColumn(name = "doctor_id")
	@JsonIgnore
	@ToString.Exclude
	private Doctor doctor;

	@ManyToOne
	@JoinColumn(name = "patient_id")
	@JsonIgnore
	@ToString.Exclude
	private Patient patient;

	@OneToOne()
	@JsonIgnore
	@ToString.Exclude
	private Prescription prescription;

	@ManyToOne
	@JoinColumn(name = "hospital_id")
	@JsonIgnore
	@ToString.Exclude
	private Hospital hospital;
	
//	@JsonIgnore
//	@OneToOne(mappedBy = "appointment")
//    private Slot slot;
	
	@OneToMany(mappedBy = "appointment")
	@JsonIgnore
	@ToString.Exclude
	private List<Message> messages = new ArrayList<>();

}
