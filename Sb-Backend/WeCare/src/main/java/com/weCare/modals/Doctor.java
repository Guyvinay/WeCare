package com.weCare.modals;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "doctors")
public class Doctor extends Profile {

	@NotBlank(message = "Doctor name cannot be blank!!!")
	private String doctor_name;

//	@NotBlank
	@Enumerated(EnumType.STRING)
	private Department department;

	@NotBlank(message = "Qualification cannot be blank!!!")
	private String qualification;

	@NotBlank(message = "Mobile cannot be blank!!!")
	private String mobile;

	@OneToOne
	private Address address;

	@OneToMany(mappedBy = "doctor")
	private List<Patient> patients = new ArrayList<>();

	@OneToMany(mappedBy = "doctor")
	private List<Prescription> prescriptions = new ArrayList<>();

	@OneToMany(mappedBy = "doctor")
	private List<Appointment> appointments = new ArrayList<>();

	@ManyToOne
	@JoinColumn(name = "hospital_id")
	private Hospital hospital;

	public Doctor(String email, String userName, String passWord, String profile_picture, Role role,
			String doctor_name, Department department, String qualification, String mobile) {
		super(email, userName, passWord, profile_picture, role);
		this.doctor_name = doctor_name;
		this.department = department;
		this.qualification = qualification;
		this.mobile = mobile;
	}
	
}
