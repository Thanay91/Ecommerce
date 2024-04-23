package com.example.demo.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter
@Entity
public class Rating extends BaseModel {
    private double rate;
    private int count;
    
}
