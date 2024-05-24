package com.project.ShoesProject.service;


import com.project.ShoesProject.dto.CategoryDTO;
import com.project.ShoesProject.entity.Category;

import java.util.List;

public interface ICategoryService {
    Category getById(Long id);
    List<Category> getAll();
    Category create(CategoryDTO categoryDTO);
    Category update(CategoryDTO categoryDTO,Long id);
    void delete(Long id);
}
