package com.weCare.modals;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "messages")
public class Message {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String message_id;

	private String message;

	private LocalDateTime timeStamp;

	private String sender;

	public Message(String message, LocalDateTime timeStamp, String sender) {
		super();
		this.message = message;
		this.timeStamp = timeStamp;
		this.sender = sender;
	}

	@ManyToOne
	@JoinColumn(name = "patient_id")
	@JsonIgnore
	private Patient patient;

	@ManyToOne
	@JoinColumn(name = "doctor_id")
	@JsonIgnore
	private Doctor doctor;

	@ManyToOne
	@JoinColumn(name = "appointment_id")
	@JsonIgnore
	private Appointment appointment;

}
