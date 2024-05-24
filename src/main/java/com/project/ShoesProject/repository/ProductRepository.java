package com.project.ShoesProject.repository;

import com.project.ShoesProject.entity.Product;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsByName(String name);
    Page<Product> findAll(Pageable pageable);//phân trang

    @Query("SELECT p FROM Product p WHERE "+
            "(p.name IS NULL OR p.name LIKE CONCAT('%',:name,'%'))" +
            "OR (p.category.id IS NULL OR p.category.id = :categoryId)"
    )
    List<Product> findProduct(String name,Long categoryId);
}
