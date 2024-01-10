package com.weCare.modals;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
