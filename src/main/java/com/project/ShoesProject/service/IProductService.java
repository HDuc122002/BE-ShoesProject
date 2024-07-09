package com.project.ShoesProject.service;

import com.project.ShoesProject.dto.ProductDTO;
import com.project.ShoesProject.entity.Product;
import com.project.ShoesProject.exception.DataNotFoundException;
import com.project.ShoesProject.response.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface IProductService {
    Product create(ProductDTO productDTO) throws DataNotFoundException;
    Product getById(Long id) throws DataNotFoundException;
    Page<ProductResponse> getAll(PageRequest pageRequest);
    Product update(Long id, ProductDTO productDTO) throws DataNotFoundException;
    void delete(Long id);
    boolean existsByName(String name);
    List<Product> search(String name,Long categoryId) throws Exception;
}
