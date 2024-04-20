package com.example.demo.controller;

import com.example.demo.exceptions.ProductNotFoundException;
import com.example.demo.models.Product;
import com.example.demo.services.FSProductService;
import com.example.demo.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
                                                        @RequestParam(required = false) Integer limit) throws ProductNotFoundException{
        ResponseEntity responseEntity;
        List<Product> products = new ArrayList<>();
        products = productService.getAllProducts(sort, limit);
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


}
