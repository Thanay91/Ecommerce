package com.example.demo.dtos;

import com.example.demo.models.Rating;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Setter
@Getter
public class ProductRequestDTO {
    private String category;
    private String title;
    private double price;
    private String description;
    private String image;
    private Rating rating;
}
