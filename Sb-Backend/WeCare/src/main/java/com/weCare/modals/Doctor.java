package com.weCare.modals;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
@ToString
@NoArgsConstructor
@Table(name = "doctors")
public class Doctor extends Profile {

	@NotBlank(message = "Doctor name cannot be blank!!!")
	private String doctor_name;

	@NotNull(message = "Department cannot be blank!!!")
	@Enumerated(EnumType.STRING)
	private Department department;

	@NotBlank(message = "Qualification cannot be blank!!!")
	private String qualification;

	@NotBlank(message = "Mobile cannot be blank!!!")
//	@Column(unique = true)
	private String mobile;

	@OneToOne
	@JsonIgnore
	@ToString.Exclude
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
