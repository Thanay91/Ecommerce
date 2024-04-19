package com.example.demo.services;

import com.example.demo.controller.ProductController;
import com.example.demo.dtos.ProductResponseDTO;
import com.example.demo.exceptions.ProductNotFoundException;
import com.example.demo.models.Product;
import com.example.demo.models.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Primary
@Qualifier("FSProductService")
public class FSProductService implements IProductService{

    @Autowired
    RestTemplate restTemplate;

    public Product getProductFromResponseDTO(ProductResponseDTO productResponseDTO){
        Product product = new Product();
        product.setId(productResponseDTO.getId());
        product.setCategory(productResponseDTO.getCategory());
        product.setImage(productResponseDTO.getImage());
        product.setTitle(productResponseDTO.getTitle());
        product.setDescription(productResponseDTO.getDescription());
        product.setRating(new Rating());
        product.getRating().setRate(productResponseDTO.getRating().getRate());
        product.getRating().setCount(productResponseDTO.getRating().getCount());
        product.setPrice(productResponseDTO.getPrice());
        return product;
    }
    @Override
    public List<Product> getAllProducts() throws ProductNotFoundException {
        List<Product> allProducts = new ArrayList<>();
        ProductResponseDTO[] response = restTemplate.getForObject("https://fakestoreapi.com/products",
                ProductResponseDTO[].class);

        for(ProductResponseDTO dto : response){
            Product p = getProductFromResponseDTO(dto);
            allProducts.add(p);
        }
        return allProducts;
    }

    @Override
    public Product getSingleProduct(Long id) throws ProductNotFoundException {
        ProductResponseDTO response = restTemplate.getForObject("https://fakestoreapi.com/products/" + id, ProductResponseDTO.class);
        if(response==null){
            throw new ProductNotFoundException("Product with id "+ id + " not found" );
        }
        Product product = getProductFromResponseDTO(response);
        return product;

    }

    @Override
    public List<Product> getLimitedProduct(int limit) {
        List<Product> limitedProducts = new ArrayList<>();
        String endPoint = "https://fakestoreapi.com/products"+"?limit=" + limit;
        ProductResponseDTO[] response = restTemplate.getForObject(endPoint, ProductResponseDTO[].class);

        for(ProductResponseDTO eachResponse: response){
            Product product = getProductFromResponseDTO(eachResponse);
            limitedProducts.add(product);
        }
        return  limitedProducts;
    }

    @Override
    public List<Product> getSortedProduct(String sortType) {
        List<Product> arrangedProducts = new ArrayList<>();
        String endPoint = "https://fakestoreapi.com/products?sort=" + sortType;
        System.out.println(endPoint);
        ProductResponseDTO[] responseDTOS = restTemplate.getForObject(endPoint, ProductResponseDTO[].class);

        for(ProductResponseDTO eachResponse : responseDTOS){
            Product p = getProductFromResponseDTO(eachResponse);
            arrangedProducts.add(p);
        }
        return arrangedProducts;
    }
}
