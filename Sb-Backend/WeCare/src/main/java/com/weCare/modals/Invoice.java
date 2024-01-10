package com.weCare.modals;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
