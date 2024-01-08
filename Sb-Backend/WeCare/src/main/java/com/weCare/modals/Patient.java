package com.weCare.modals;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "patients")
public class Patient extends Profile {

    @NotBlank(message = "Patient name cannot be blank!!!")
    private String patient_name;

    @Enumerated(EnumType.STRING)
    private Gender patient_gender;

    @NotNull(message = "date_of_birth cannot be blank!!!")
    private LocalDate date_of_birth;

    @NotBlank(message = "Mobile cannot be blank!!!")
    @Column(unique = true)
    private String mobile;

    @Enumerated(EnumType.STRING)
    private AppointmentStatus appointment_status;

    public Patient(String email, String userName, String passWord, String profile_picture, Role role,
			String patient_name, Gender patient_gender, LocalDate date_of_birth, String mobile, AppointmentStatus appointment_status) {
		super(email, userName, passWord, profile_picture, role);
		this.patient_name = patient_name;
		this.patient_gender = patient_gender;
		this.date_of_birth = date_of_birth;
		this.mobile = mobile;
		this.appointment_status = appointment_status;
	}
    
    @ManyToOne
    @JoinColumn(name = "doctor_id")
    @JsonIgnore
    private Doctor doctor;

    @OneToOne
    @JsonIgnore
    private Address address;
    
	@OneToMany(mappedBy = "patient")
    @JsonIgnore
    private List<Prescription> prescriptions = new ArrayList<>();

    @OneToMany(mappedBy = "patient")
    @JsonIgnore
    private List<Invoice> invoices = new ArrayList<>();

    @OneToMany(mappedBy = "patient")
    @JsonIgnore
    private List<Appointment> appointments =  new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "patient_hospital",
            joinColumns = @JoinColumn(name = "patient_id"),
            inverseJoinColumns = @JoinColumn(name = "hospital_id")
    )
    @JsonIgnore
    private List<Hospital> hospitals = new ArrayList<>();

}
