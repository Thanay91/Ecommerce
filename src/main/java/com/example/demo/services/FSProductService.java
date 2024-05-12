package com.example.demo.services;

import com.example.demo.dtos.ProductResponseDTO;
import com.example.demo.exceptions.ProductNotFoundException;
import com.example.demo.models.Product;
import com.example.demo.models.Rating;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
//@Primary
//@Qualifier("FSProductService")
public class FSProductService implements IProductService {

    @Autowired
    RestTemplate restTemplate;

    public Product getProductFromResponseDTO(ProductResponseDTO productResponseDTO) {
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
    public List<Product> getAllProducts(String sortType, Integer limit, HttpServletRequest requestURL) throws ProductNotFoundException {
        List<Product> products = new ArrayList<>();
        String endPoint= "https://fakestoreapi.com/products?"+requestURL.getQueryString();
        ProductResponseDTO[] response = restTemplate.getForObject(endPoint,
                ProductResponseDTO[].class);

        for(ProductResponseDTO res: response){
            Product product = getProductFromResponseDTO(res);
            products.add(product);
        }

        return products;
    }

    @Override
    public Product getSingleProduct(Long id) throws ProductNotFoundException {
        ProductResponseDTO response = restTemplate.getForObject("https://fakestoreapi.com/products/" + id, ProductResponseDTO.class);
        if (response == null) {
            throw new ProductNotFoundException("Product with id " + id + " not found");
        }
        Product product = getProductFromResponseDTO(response);
        return product;

    }

    @Override
    public List<Product> getLimitedProduct(int limit) {
        List<Product> limitedProducts = new ArrayList<>();
        String endPoint = "https://fakestoreapi.com/products" + "?limit=" + limit;
        ProductResponseDTO[] response = restTemplate.getForObject(endPoint, ProductResponseDTO[].class);

        for (ProductResponseDTO eachResponse : response) {
            Product product = getProductFromResponseDTO(eachResponse);
            limitedProducts.add(product);
        }
        return limitedProducts;
    }

    @Override
    public List<String> getAllCategories() {
        List<String> categories = new ArrayList<>();
        String[] response = restTemplate.getForObject("https://fakestoreapi.com/products/categories",
                String[].class);
        for (String cat : response) {
            categories.add(cat);

        }
        return categories;
    }

    @Override
    public List<Product> getInCategory(String category) {
        List<Product> products = new ArrayList<>();
        String endPoint = "https://fakestoreapi.com/products/category/" + category;

        ProductResponseDTO[] responseDTOS = restTemplate.getForObject(endPoint, ProductResponseDTO[].class);

        for (ProductResponseDTO response : responseDTOS) {
            Product p = getProductFromResponseDTO(response);
            products.add(p);
        }
        return products;
    }

    @Override
    public Product addProduct(Product product) {
        return null;
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        return null;
    }
}