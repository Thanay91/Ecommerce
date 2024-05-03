package com.example.demo.services;

import com.example.demo.dtos.ProductResponseDTO;
import com.example.demo.exceptions.ProductNotFoundException;
import com.example.demo.models.Product;
import com.example.demo.repository.ProductRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.hibernate.query.SortDirection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
@Primary
@Qualifier("ProductService")

public class ProductService implements IProductService{
    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts(String sortType, Integer limit, HttpServletRequest requestURL) throws ProductNotFoundException {
       if(limit==null && sortType==null) {
           Optional<List<Product>> optionalProducts = productRepository.getAllBy();
           if (optionalProducts.isEmpty()) {
               throw new ProductNotFoundException("No products in database");
           }
           return optionalProducts.get();
       }
       Sort sort = Sort.by("id").ascending();
       if(sortType!=null && sortType.equals("desc")){
           sort = Sort.by("id").descending();
       }
       limit = (limit==null)? 1000: limit;
       Pageable pageable = PageRequest.of(0,limit,sort);
       List<Product> products = productRepository.findAllBy(pageable);
        /*String param = requestURL.getQueryString();
        Pageable pageable = PageRequest.of(1,limit,Sort.by(sortType));
        List<Product> products = (List<Product>) productRepository.findAllBy(pageable);
        return products;*/
        return products;
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

    public Product addProduct(Product product){
        Product savedProduct = productRepository.save(product);
        return savedProduct;
    }

    public Product updateProduct(Long id, Product product){
        Product toBeUpdated = new Product();
        toBeUpdated.setId(id);
        toBeUpdated.setTitle(product.getTitle());
        toBeUpdated.setRating(product.getRating());
        toBeUpdated.setImage(product.getImage());
        toBeUpdated.setDescription(product.getDescription());
        Product updatedProduct = productRepository.save(toBeUpdated);
        return updatedProduct;
    }

}
