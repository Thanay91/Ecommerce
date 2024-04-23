package com.example.demo.dtos;

import com.example.demo.models.Address;
import com.example.demo.models.FullName;
import com.example.demo.models.GeoLocation;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Embedded;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserResponseDTO {
    private Long id;
    private AddressDTO address;
    private String email;
    private String username;
    private String phone;
    @JsonProperty("name")
    private fullnameDTO fullname;
    private String password;

    // Getters and setters

    @Setter
    @Getter
    public static class AddressDTO {
        private geolocationDTO geolocation;
        private String city;
        private String street;
        private String number;
        private String zipcode;

        // Getters and setters
    }
    @Setter
    @Getter
    public static class geolocationDTO {
        private String lat;
        @JsonProperty("long")
        private String lng;

        // Getters and setters
    }
    @Setter
    @Getter
    public static class fullnameDTO {
        private String firstname;
        private String lastname;

        // Getters and setters
    }
}
