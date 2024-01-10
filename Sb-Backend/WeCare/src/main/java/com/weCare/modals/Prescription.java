package com.weCare.modals;

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
    private List<String> prescription_medications = new ArrayList<>();

    private String additional_instructions;

    @OneToOne(mappedBy = "prescription")
    private Appointment  appointment;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

}
