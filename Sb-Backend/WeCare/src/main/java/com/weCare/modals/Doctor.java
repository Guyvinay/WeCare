package com.weCare.modals;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Doctor extends Profile {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String doctor_id;
	
	private String doctor_name;
	
	private String specialization;

	private String mobile;

	@OneToMany(mappedBy = "doctor")
	private List<Address> addresses = new ArrayList<>();

	@OneToMany(mappedBy = "doctor")
	private List<Patient> patients = new ArrayList<>();

	@OneToMany(mappedBy = "doctor")
	private List<Prescription> prescriptions = new ArrayList<>();
	
}
