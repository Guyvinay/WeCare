package com.weCare.modals;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
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
    
    @ElementCollection
    @CollectionTable(
            name = "prescription_medications",
            joinColumns = @JoinColumn(name = "prescription_id")
    )
//    @JsonIgnore
    private List<String> prescription_medications = new ArrayList<>();

    private String additional_instructions;

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
