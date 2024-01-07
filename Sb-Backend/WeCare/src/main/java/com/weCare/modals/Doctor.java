package com.weCare.modals;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "doctors")
public class Doctor extends Profile {

	private String doctor_name;

	@Enumerated(EnumType.STRING)
	private Department department;

	private String qualification;


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
