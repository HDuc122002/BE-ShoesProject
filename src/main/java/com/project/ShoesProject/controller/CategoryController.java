package com.project.ShoesProject.controller;

import com.project.ShoesProject.dto.CategoryDTO;
import com.project.ShoesProject.entity.Category;
import com.project.ShoesProject.service.Impl.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("")
    public List<Category> getListCategory() {
        return categoryService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable Long id){
        try {
            return ResponseEntity.ok(categoryService.getById(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Category not found with ID: "+id);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody CategoryDTO categoryDTO){
        try {
            categoryService.create(categoryDTO);
            return ResponseEntity.ok().body("Category created successfully");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@RequestBody CategoryDTO categoryDTO,@PathVariable Long id){
        try {
            categoryService.update(categoryDTO,id);
            return ResponseEntity.ok().body("Category Updated successfully");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
        categoryService.delete(id);
    }
}
