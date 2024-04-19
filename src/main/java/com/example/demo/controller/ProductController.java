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
        if(limit!=null && sort != null){
            products =  productService.getLimitedProduct(limit);
            if(sort.equals("asc")){
                Collections.sort(products, (a,b) -> (int)(a.getId()-b.getId()));
            }
            else if(sort.equals("desc")){
                Collections.sort(products, (a,b) -> (int)(b.getId()-a.getId()));
            }
            responseEntity = new ResponseEntity(products, HttpStatus.OK);
            return responseEntity;
        }
        if(limit !=null){
            products = productService.getLimitedProduct(limit);
            responseEntity = new ResponseEntity(products, HttpStatus.OK);
            return responseEntity;
        }
        if(sort!=null ){
            products = productService.getAllProducts();
            if(sort.equals("asc")){
                Collections.sort(products, (a,b) -> (int)(a.getId()-b.getId()));
            }
            else if(sort.equals("desc")){
                Collections.sort(products, (a,b) -> (int)(b.getId()-a.getId()));
            }
            responseEntity = new ResponseEntity(products, HttpStatus.OK);
            return responseEntity;
        }
        products = productService.getAllProducts();
        responseEntity = new ResponseEntity(products, HttpStatus.OK);
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getSingleProduct(@PathVariable("id") Long id) throws ProductNotFoundException {
        ResponseEntity responseEntity;
        Product p =  productService.getSingleProduct(id);
        responseEntity = new ResponseEntity(p, HttpStatus.OK);
        return responseEntity;
    }

    /*@GetMapping(params = "limit")
    public List<Product> getLimitedProduct(@RequestParam int limit){
        List<Product> products = productService.getLimitedProduct(limit);
        return products;
    }

    @GetMapping(params = "sortType")
    public List<Product> getSortedProduct(@RequestParam String sortType){
        List<Product> arrangedProducts = productService.getSortedProduct(sortType);
        return arrangedProducts;
    }*/


}
