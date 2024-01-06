package com.weCare.modals;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
@Data
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
