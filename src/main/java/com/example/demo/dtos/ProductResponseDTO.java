package com.example.demo.dtos;

import com.example.demo.models.Rating;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponseDTO {
    private Long id;
    private String category;
    private String title;
    private double price;
    private String description;
    private String image;
    private Rating rating;
}
