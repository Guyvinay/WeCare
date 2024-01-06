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
@Table(name = "invoices")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private  String invoice_id;

    private Double total_amount;

    @ElementCollection
    @CollectionTable(name = "invoice_services", joinColumns = @JoinColumn(name = "invoice_id"))
    private List<String> invoice_services = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;


    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;



}
