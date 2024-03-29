package com.weCare.modals;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
{
        "locality":"Sandalpur",
        "city":"Patna",
        "zip_code":800006,
        "state":"Bihar",
        "Country":"India"
}
*/
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "addresses")
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String address_id;

	private String locality;

	private String city;

	private Integer zip_code;

	private String state;

	private String country;
}
