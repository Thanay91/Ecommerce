package com.example.demo.repository;


import com.example.demo.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("select p from Product p")
    public Optional<List<Product>> getAllBy();

    @Query("select p from Product p where p.id = ?1")
    Optional<Product> getProductsById(Long id);

    @Query(value = "select * from Product p limit :limitResult", nativeQuery = true)
    public List<Product> getAllBy(int limitResult);

    @Query(value = "select distinct category from Product p", nativeQuery = true)
    public List<String> getAllCategories();

    @Query("select p from Product p where p.category = ?1")
    public List<Product> getProductsByCategory(String Category);

    public Product save(Product product);

    @Query("select p from Product p")
    public List<Product> findAllBy(Pageable pageable);
}
