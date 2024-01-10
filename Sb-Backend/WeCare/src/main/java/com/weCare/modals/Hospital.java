package com.weCare.modals;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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

    @NotBlank(message = "Hospital name cannot be blank!!!")
    private String hospital_name;

    @NotNull(message = "contact cannot be blank!!!")
    @Column(unique = true)
    private String contact;


    private String description;

    @OneToOne
//    @JsonIgnore
//    @ToString.Exclude
    private Address address;

    @OneToMany(mappedBy = "hospital")
//    @JsonIgnore
    @ToString.Exclude
    private List<Doctor> doctors = new ArrayList<>();

    @ManyToMany()
    @JoinTable(
            name = "patient_hospital",
            joinColumns = @JoinColumn(name = "patient_id"),
            inverseJoinColumns = @JoinColumn(name = "hospital_id")
    )
//    @JsonIgnore
    @ToString.Exclude
    private List<Patient> patients = new ArrayList<>();

    @OneToMany(mappedBy = "hospital")
    @JsonIgnore
    @ToString.Exclude
    private List<Appointment> appointments = new ArrayList<>();

}
