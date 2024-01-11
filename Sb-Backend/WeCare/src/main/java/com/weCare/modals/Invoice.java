package com.weCare.modals;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
    @CollectionTable(
    		name = "invoice_medications",
    		joinColumns = @JoinColumn(name = "invoice_id")
    )
    @MapKeyColumn(name = "medication_id")
    @Column(name = "medication_quantity")
    private Map<String, Integer> medications_invoice = new HashMap<>();

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @OneToOne()
    @JsonIgnore
    @ToString.Exclude
    private Prescription prescription;

}
