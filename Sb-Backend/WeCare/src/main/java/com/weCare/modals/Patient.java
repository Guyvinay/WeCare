package com.weCare.modals;

import jakarta.persistence.*;
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

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private  String prescription_id;
    
    private String name;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private LocalDate date_of_birth;

    private String mobile;

    @Enumerated(EnumType.STRING)
    private AppointmentStatus appointment_status;

    
    
    
    public Patient(String profile_id, String email, String userName, String passWord, String profile_picture, Role role,
			String name, Gender gender, LocalDate date_of_birth, String mobile, AppointmentStatus appointment_status) {
		super(profile_id, email, userName, passWord, profile_picture, role);
		this.name = name;
		this.gender = gender;
		this.date_of_birth = date_of_birth;
		this.mobile = mobile;
		this.appointment_status = appointment_status;
	}
    
    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;


	@OneToMany(mappedBy = "patient")
    private List<Prescription> prescriptions = new ArrayList<>();

    @OneToMany(mappedBy = "patient")
    private List<Invoice> invoices = new ArrayList<>();

    @OneToMany(mappedBy = "patient")
    private List<Appointment> appointments =  new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

}
