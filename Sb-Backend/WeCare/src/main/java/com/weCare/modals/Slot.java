package com.weCare.modals;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "slots")
public class Slot {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String  slotId;
	
	private String slotDescription;
	
	private LocalDateTime startTime;
    private LocalDateTime endTime;
	
    @Enumerated(EnumType.STRING)
	private SlotPeriod slotPeriod;
	
	@Enumerated(EnumType.STRING)
	private SlotStatus slotStatus;
	
	@JsonIgnore
	@ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;
	
	/*
	@ManyToMany(mappedBy = "slots")
	private List<Doctor> doctors;
	
	@OneToOne
	@JoinColumn(name = "appointment_id")
	private Appointment appointment;*/
}
