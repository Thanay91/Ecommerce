package com.example.demo.controllerAdvisor;

import com.example.demo.controller.ProductController;
import com.example.demo.dtos.ErrorResponseDTO;
import com.example.demo.dtos.ProductResponseDTO;
import com.example.demo.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerAdvisor {


    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ProductResponseDTO> handleProductNotFoundException(ProductNotFoundException exception){
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
        errorResponseDTO.setMessage(exception.getMessage() + "Global Response");
        ResponseEntity responseEntity = new ResponseEntity(errorResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        return responseEntity;

    }
}
