package com.example.demo.models;

import com.fasterxml.jackson.databind.ser.Serializers;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@Setter
@Entity
public class Product extends BaseModel {
    private Long id;
    private String category;
    private String title;
    private double price;
    private String description;
    private String image;

    //P  :  R
    //1  :  M
    //1  :  1
    @OneToOne
    public Rating rating;
}
