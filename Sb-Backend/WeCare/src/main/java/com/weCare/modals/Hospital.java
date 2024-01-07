package com.weCare.modals;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "hospitals")
public class Hospital {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private  String hospital_id;

    private String hospital_name;

    private String contact;

    private String description;

    @OneToOne
    @PrimaryKeyJoinColumn(name = "address_id")
    private Address address;

    @OneToMany(mappedBy = "hospital")
    private List<Doctor> doctors = new ArrayList<>();

    @ManyToMany(mappedBy = "hospitals")
    private List<Patient> patients = new ArrayList<>();

    @OneToMany(mappedBy = "hospital")
    private List<Appointment> appointments = new ArrayList<>();

}
