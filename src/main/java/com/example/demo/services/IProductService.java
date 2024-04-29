package com.example.demo.services;

import com.example.demo.exceptions.ProductNotFoundException;
import com.example.demo.models.Product;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.Optional;

public interface IProductService {
    List<Product> getAllProducts(String sortType, Integer limit, HttpServletRequest requestURL) throws ProductNotFoundException ;

    Product getSingleProduct(Long id) throws ProductNotFoundException;

    List<Product> getLimitedProduct(int numbers);

    List<String> getAllCategories();

    List<Product> getInCategory(String category);

    Product addProduct(Product product);

    Product updateProduct(Long id, Product product);

}
