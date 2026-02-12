package com.weCare.test;

import java.time.LocalDate;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

public class TestEntity {

	@NotNull(message = "Medication Name cannot be blank!!!")
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

}
