package com.weCare.modals;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "doctors")
@PrimaryKeyJoinColumn(name = "doctor_id")
public class Doctor extends Profile {

	@NotBlank(message = "Doctor name cannot be blank!!!")
	private String name;

	@NotNull(message = "Department cannot be blank!!!")
	@Enumerated(EnumType.STRING)
	private Department department;
	
	@NotNull(message = "Gender cannot be blank!!!")
	@Enumerated(EnumType.STRING)
	private Gender gender;

	@NotBlank(message = "Qualification cannot be blank!!!")
	private String qualification;

	@NotBlank(message = "Mobile cannot be blank!!!")
	@Column(unique = true)
	private String mobile;
	
	@NotNull(message = "Availability cannot be null!!!")
	@Enumerated(EnumType.STRING)
	private Availability availability;
	
	

	public Doctor(String email, String passWord, String profile_picture, Role role,
			@NotBlank(message = "Doctor name cannot be blank!!!") String name,
			@NotNull(message = "Department cannot be blank!!!") Department department,
			@NotNull(message = "Gender cannot be blank!!!") Gender gender,
			@NotBlank(message = "Qualification cannot be blank!!!") String qualification,
			@NotBlank(message = "Mobile cannot be blank!!!") String mobile,
			@NotNull(message = "Availability cannot be null!!!") Availability availability) {
		super(email, passWord, profile_picture, role);
		this.name = name;
		this.department = department;
		this.gender = gender;
		this.qualification = qualification;
		this.mobile = mobile;
		this.availability = availability;
	}

	@OneToOne
//	@JsonIgnore
	@ToString.Exclude
	@JoinColumn(name = "address_id")
	private Address address;

	@ManyToMany()
	@JoinTable(
			name = "doctor_patient",
			joinColumns = @JoinColumn(name = "patient_id"),
			inverseJoinColumns = @JoinColumn(name = "doctor_id")
	)
	@JsonIgnore
	@ToString.Exclude
	private List<Patient> patients = new ArrayList<>();

	@OneToMany(mappedBy = "doctor")
	@JsonIgnore
	@ToString.Exclude
	private List<Prescription> prescriptions = new ArrayList<>();

	@OneToMany(mappedBy = "doctor")
	@JsonIgnore
	@ToString.Exclude
	private List<Appointment> appointments = new ArrayList<>();

	@ManyToOne
	@JoinColumn(name = "hospital_id")
	@JsonIgnore
	@ToString.Exclude
//	@JsonBackReference
	private Hospital hospital;
	
	@JsonIgnore
	@OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    private List<Slot> slots = new ArrayList<>();
	
	/*
	@ManyToMany()
	@JoinTable(
			name = "doctor_slots",
			joinColumns = @JoinColumn(name="slotId"),
			inverseJoinColumns = @JoinColumn(name="doctor_id")
			)
	private List<Slot> slots;*/
	
	@OneToMany(mappedBy = "doctor")
	@JsonIgnore
	@ToString.Exclude
	private List<Message> messages = new ArrayList<>();

	
}
