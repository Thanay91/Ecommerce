package com.example.demo.services;

import com.example.demo.exceptions.ProductNotFoundException;
import com.example.demo.models.Product;

import java.util.List;

public interface IProductService {
    List<Product> getAllProducts(String sortType, Integer limit) throws ProductNotFoundException ;

    Product getSingleProduct(Long id) throws ProductNotFoundException;

    List<Product> getLimitedProduct(int numbers);

}
