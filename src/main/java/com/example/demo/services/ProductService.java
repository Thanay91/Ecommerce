package com.example.demo.services;

import com.example.demo.dtos.ProductResponseDTO;
import com.example.demo.exceptions.ProductNotFoundException;
import com.example.demo.models.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Primary
@Qualifier("ProductService")

public class ProductService implements IProductService{
    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts(String sortType, Integer limit) throws ProductNotFoundException {
        List<Product> allProducts = new ArrayList<>();
        if(limit != null){
            allProducts =  this.getLimitedProduct(limit);
        }
        else{
            Optional<List<Product>> allProductsOptional = productRepository.getAllBy();
            if(allProductsOptional.isEmpty()){
                throw new ProductNotFoundException("Product not found");
            }
            allProducts = allProductsOptional.get();
        }
        if(sortType !=null){
            if(sortType.equals("asc")){
                Collections.sort(allProducts, (a, b) -> (int)(a.getId()-b.getId()));
            }
            else if(sortType.equals("desc")){
                Collections.sort(allProducts, (a,b) -> (int)(b.getId()-a.getId()));
            }
        }
        return allProducts;
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
    public List<String> getAllCategories() {
        return productRepository.getAllCategories();
    }

    @Override
    public List<Product> getInCategory(String category) {
        List<Product> products = productRepository.getProductsByCategory(category);
        return products;
    }

}
