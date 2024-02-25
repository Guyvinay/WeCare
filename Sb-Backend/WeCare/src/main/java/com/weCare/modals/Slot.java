package com.weCare.modals;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
//@Table(name = "slots")
public class Slot {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String  slotId;
	
	private String slotDescription;
	private LocalDate slotStarts;
	private LocalDate slotEnds;
	
	@Enumerated(EnumType.STRING)
	private SlotStatus slotStatus;
	
	/*
	@ManyToMany(mappedBy = "slots")
	private List<Doctor> doctors;
	
	@OneToOne
	@JoinColumn(name = "appointment_id")
	private Appointment appointment;*/
}
