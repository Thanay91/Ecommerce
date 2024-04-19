package com.example.demo.exceptions;

import com.example.demo.controller.ProductController;

public class ProductNotFoundException extends Exception{

    public ProductNotFoundException(String message){
        super(message);
    }

}
