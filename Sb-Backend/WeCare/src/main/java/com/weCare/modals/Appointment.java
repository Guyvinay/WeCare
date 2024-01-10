package com.weCare.modals;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "appointments")
public class Appointment {
	
	@Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private  String appointment_id;

    private LocalDateTime booking_time;
    
    private LocalDate appointment_date;

    @NotNull(message = "Department cannot be blank!!!")
    @Enumerated(EnumType.STRING)
    private Department department;
    
    @NotBlank(message = "Provide details about ailments!!!")
    private String ailment_descriptio;

    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    @JsonIgnore
    @ToString.Exclude
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    @JsonIgnore
    @ToString.Exclude
    private Patient patient;

    @OneToOne()
    @JsonIgnore
    @ToString.Exclude
    private Prescription prescription;

    @ManyToOne
    @JoinColumn(name = "hospital_id")
    @JsonIgnore
    @ToString.Exclude
    private Hospital hospital;


}
