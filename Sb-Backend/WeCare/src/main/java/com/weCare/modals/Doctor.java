package com.weCare.modals;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Doctor extends Profile {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String doctor_id;
	
	private String doctor_name;
	
	private String specialization;

	private String qualification;


	private String mobile;

	@OneToMany(mappedBy = "doctor")
	private List<Address> addresses = new ArrayList<>();

	@OneToMany(mappedBy = "doctor")
	private List<Patient> patients = new ArrayList<>();

	@OneToMany(mappedBy = "doctor")
	private List<Prescription> prescriptions = new ArrayList<>();

	@OneToMany(mappedBy = "doctor")
	private List<Appointment> appointments = new ArrayList<>();

	@ManyToOne
	@JoinColumn(name = "hospital_id")
	private Hospital hospital;

	public Doctor(String profile_id, String email, String userName, String passWord, String profile_picture, Role role,
			String doctor_name, String specialization, String qualification, String mobile) {
		super(profile_id, email, userName, passWord, profile_picture, role);
		this.doctor_name = doctor_name;
		this.specialization = specialization;
		this.qualification = qualification;
		this.mobile = mobile;
	}
	
}
