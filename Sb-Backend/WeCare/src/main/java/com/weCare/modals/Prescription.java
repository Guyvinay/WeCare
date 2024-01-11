package com.weCare.modals;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "prescriptions")
public class Prescription {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private  String prescription_id;

    private LocalDateTime prescription_date;

    private String additional_instructions;


//    Using prescription_medications_quantity as table name will create a seperate table 
//    while using prescription_medications as table name will add only medicine quanitity 
//    column to prescription_medication table
    @ElementCollection
    @CollectionTable(
    		name = "prescription_medication_quantity",
    		joinColumns = @JoinColumn(name = "prescription_id")
    )
    @MapKeyColumn(name = "medication_id")
    @Column(name = "medication_quantity")
    private Map<String,Integer> medication_ids;
    
    @ManyToMany
    @JoinTable(
            name = "prescription_medications",
            joinColumns = @JoinColumn(name = "prescription_id"),
            inverseJoinColumns = @JoinColumn(name = "medication_id")
    )
//    @JsonIgnore
    private List<Medication> medications = new ArrayList<>();


    @OneToOne(mappedBy = "prescription")
    @JsonIgnore
    private Appointment  appointment;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    @JsonIgnore
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    @JsonIgnore
    private Doctor doctor;

}
