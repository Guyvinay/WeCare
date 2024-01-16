package com.weCare.modals;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "medications")
public class Medication {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String medication_id;

	@NotNull(message = "Medication Name cannot be blank!!!")
	@Column(unique = true)
	private String medication_name;

	private String medication_description;

	@NotNull(message = "Medication Manufacturer cannot be blank!!!")
	private String medication_manufacturer;

	@NotNull(message = "Medication Price cannot be blank!!!")
	@DecimalMin(value = ".99", inclusive = true, message = "medication price must be greater than '1.00'")
	@DecimalMax(value = "9999999.9", inclusive = true, message = "medication price must be less than '9999999.9'")
	private Double medication_price;

	@NotNull(message = "Medication Quantity cannot be blank!!!")
	@Digits(integer = 6, message = "Medication quantity must be an integer type!!!", fraction = 0)
	private Integer medication_quantity;

	@NotNull(message = "Expiration Date cannot be blank!!!")
	@Future(message = "Medication expiration must be in future!!!")
	private LocalDate expirationDate;

	@ManyToMany(mappedBy = "medications")
	@JsonIgnore
	@ToString.Exclude
	private List<Prescription> prescriptions = new ArrayList<>();

	@ManyToMany(mappedBy = "medications")
	@JsonIgnore
	@ToString.Exclude
	private List<Invoice> invoices = new ArrayList<>();

}