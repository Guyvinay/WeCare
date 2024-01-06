package com.weCare.modals;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "patients")
public class Patient {

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

    @ManyToOne
    private Doctor doctor;

    @OneToMany(mappedBy = "patient")
    private List<Prescription> prescriptions = new ArrayList<>();

}
