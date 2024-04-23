package com.example.demo.models;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GeoLocation{
    String lat;

    @JsonProperty("long")
    String lng;
}
