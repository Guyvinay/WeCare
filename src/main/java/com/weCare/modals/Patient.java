package com.weCare.modals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "patients")
@PrimaryKeyJoinColumn(name = "patient_id")
public class Patient extends Profile {

	@NotBlank(message = "Patient name cannot be blank!!!")
	private String name;

	@NotNull(message = "Gender cannot be blank!!!")
	@Enumerated(EnumType.STRING)
	private Gender gender;

	@NotNull(message = "date_of_birth cannot be blank!!!")
	private LocalDate dateOfBirth;

	@NotBlank(message = "Mobile cannot be blank!!!")
	@Column(unique = true)
	private String mobile;
	
	public Patient(String email, String passWord, String profile_picture, Role role,
			@NotBlank(message = "Patient name cannot be blank!!!") String name,
			@NotNull(message = "Gender cannot be blank!!!") Gender gender,
			@NotNull(message = "date_of_birth cannot be blank!!!") LocalDate dateOfBirth,
			@NotBlank(message = "Mobile cannot be blank!!!") String mobile) {
		super(email, passWord, profile_picture, role);
		this.name = name;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.mobile = mobile;
	}

	@ManyToMany(mappedBy = "patients")
	@JsonIgnore
	private List<Doctor> doctors;

	@OneToOne
//    @JsonIgnore
	private Address address;

	@OneToMany(mappedBy = "patient")
	@JsonIgnore
	private List<Prescription> prescriptions = new ArrayList<>();

	@OneToMany(mappedBy = "patient")
	@JsonIgnore
	private List<Appointment> appointments = new ArrayList<>();

	@ManyToMany(mappedBy = "patients")
	@JsonIgnore
	private List<Hospital> hospitals = new ArrayList<>();

	@OneToMany(mappedBy = "patient")
	@JsonIgnore
	private List<Message> messages = new ArrayList<>();
	
	

}
