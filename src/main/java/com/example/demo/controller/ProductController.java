package com.example.demo.controller;

import com.example.demo.dtos.ProductRequestDTO;
import com.example.demo.dtos.ProductResponseDTO;
import com.example.demo.exceptions.ProductNotFoundException;
import com.example.demo.models.Product;
import com.example.demo.models.Rating;
import com.example.demo.services.FSProductService;
import com.example.demo.services.IProductService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.HttpServletBean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private IProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(@RequestParam(required = false) String sort,
                                                        @RequestParam(required = false) Integer limit,
                                                        HttpServletRequest request) throws ProductNotFoundException{
        ResponseEntity responseEntity;
        List<Product> products = productService.getAllProducts(sort, limit, request);
        responseEntity = new ResponseEntity(products, HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getSingleProduct(@PathVariable("id") Long id) throws ProductNotFoundException {
        ResponseEntity responseEntity;
        Product p =  productService.getSingleProduct(id);
        responseEntity = new ResponseEntity(p, HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping("/categories")
    public ResponseEntity<List<String>> getAllCategories(){
        List<String> categories = productService.getAllCategories();
        ResponseEntity responseEntity;
        responseEntity = new ResponseEntity(categories, HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping("/categories/{category}")
    public List<Product> getInCategory(@PathVariable("category") String category){
        return productService.getInCategory(category);
    }

    @PostMapping()
    public Product addProduct(@RequestBody ProductRequestDTO requestDTO){
        Product product = convertRequestDTOToProduct(requestDTO);
        Product savedProduct = productService.addProduct(product);
        return product;
    }

    @PostMapping("/{id}")
    public Product updateProduct(@PathVariable("id") Long id ,@RequestBody ProductRequestDTO requestDTO){
        Product product = convertRequestDTOToProduct(requestDTO);
        product.setId(id);
        Product updatedProduct = productService.updateProduct(product.getId(), product);
        return product;
    }

    public Product convertRequestDTOToProduct(ProductRequestDTO requestDTO){
        Product product= new Product();
        if(requestDTO.getTitle() != null){
            product.setTitle(requestDTO.getTitle());
        }
        if(requestDTO.getCategory() != null){
            product.setCategory(requestDTO.getCategory());
        }
        if(requestDTO.getImage() != null){
            product.setImage(requestDTO.getImage());
        }
        if(requestDTO.getPrice() != 0){
            product.setPrice(requestDTO.getPrice());
        }
        if(requestDTO.getDescription() != null){
            product.setDescription(requestDTO.getDescription());
        }
        if(requestDTO.getRating()!=null){
            Rating rating = requestDTO.getRating();
            product.setRating(rating);
        }
        return product;
    }

}
