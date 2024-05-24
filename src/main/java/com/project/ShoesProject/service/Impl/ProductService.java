package com.project.ShoesProject.service.Impl;

import com.project.ShoesProject.dto.ProductDTO;
import com.project.ShoesProject.entity.Category;
import com.project.ShoesProject.entity.Product;
import com.project.ShoesProject.exception.DataNotFoundException;
import com.project.ShoesProject.repository.CategoryRepository;
import com.project.ShoesProject.repository.ProductRepository;
import com.project.ShoesProject.response.ProductListResponse;
import com.project.ShoesProject.response.ProductResponse;
import com.project.ShoesProject.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public Product create(ProductDTO productDTO) throws DataNotFoundException {
        Category existingCategory = categoryRepository.findById(productDTO.getCategoryId())
                .orElseThrow(() -> new DataNotFoundException("Category not found"));
        Product newProduct = Product
                .builder()
                .name(productDTO.getName())
                .price(productDTO.getPrice())
                .thumbnail(productDTO.getThumbnail())
                .description(productDTO.getDescription())
                .category(existingCategory)
                .build();

        return productRepository.save(newProduct);
    }

    @Override
    public Product getById(Long id) throws DataNotFoundException {
        return productRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Product not found"));
    }

    @Override
    public Page<ProductResponse> getAll(PageRequest pageRequest) {
        return productRepository.findAll(pageRequest).map(ProductResponse::fromProduct);
    }

    @Override
    public Product update(Long productId, ProductDTO productDTO) throws DataNotFoundException {
        Product existingProduct = getById(productId);
        if (existingProduct != null){
            Category existingCategory = categoryRepository.findById(productDTO.getCategoryId())
                    .orElseThrow(() -> new DataNotFoundException("Category not found"));
            existingProduct.setName(productDTO.getName());
            existingProduct.setPrice(productDTO.getPrice());
            existingProduct.setThumbnail(productDTO.getThumbnail());
            existingProduct.setDescription(productDTO.getDescription());
            existingProduct.setCategory(existingCategory);

            return productRepository.save(existingProduct);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        optionalProduct.ifPresent(productRepository::delete);
    }

    @Override
    public boolean existsByName(String name) {
        return productRepository.existsByName(name);
    }

    @Override
    public List<Product> search(String name,Long categoryId) throws Exception{
        List<Product> responses = productRepository.findProduct(name,categoryId);
        return responses;
    }
}
