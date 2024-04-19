package com.example.demo.services;

import com.example.demo.exceptions.ProductNotFoundException;
import com.example.demo.models.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
//@Primary
//@Qualifier("ProductService")

public class ProductService implements IProductService{
    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() throws ProductNotFoundException {
        Optional<List<Product>> allProductsOptional = productRepository.getAllBy();
        if(allProductsOptional.isEmpty()){
            throw new ProductNotFoundException("Product list is empty");
        }
        else{
            List<Product> products = allProductsOptional.get();
            return products;
        }
    }

    @Override
    public Product getSingleProduct(Long id) throws ProductNotFoundException {
        Optional<Product> optionalProduct = productRepository.getProductsById(id);
        if (optionalProduct.isEmpty()){
            throw new ProductNotFoundException("Product with ID" + id + " not found");
        }
        else{
            Product product = optionalProduct.get();
            return product;
        }
    }

    @Override
    public List<Product> getLimitedProduct(int limit) {
        List<Product> products = productRepository.getAllBy(limit);
        return products;
    }

    @Override
    public List<Product> getSortedProduct(String sortType) {
        return null;
    }
}
