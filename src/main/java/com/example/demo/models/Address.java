package com.example.demo.models;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Address extends BaseModel{
    @Embedded
    GeoLocation geoLocation;
    String city;
    String street;
    String number;
    String zipCode;
}
