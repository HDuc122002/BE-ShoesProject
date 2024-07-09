package com.project.ShoesProject.service.Impl;

import com.project.ShoesProject.dto.CategoryDTO;
import com.project.ShoesProject.entity.Category;
import com.project.ShoesProject.repository.CategoryRepository;
import com.project.ShoesProject.service.ICategoryService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService {
    private final CategoryRepository categoryRepository;
    @Override
    public Category getById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found with ID: "+id));
    }

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category create(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setName(categoryDTO.getName());
        return categoryRepository.save(category);
    }

    @Override
    public Category update(CategoryDTO categoryDTO, Long id) {
        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found with ID: "+id));
        existingCategory.setName(categoryDTO.getName());
        categoryRepository.save(existingCategory);

        return existingCategory;
    }

    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }
}
