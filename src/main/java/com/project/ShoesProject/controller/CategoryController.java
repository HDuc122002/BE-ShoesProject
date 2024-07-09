package com.project.ShoesProject.controller;

import com.project.ShoesProject.dto.CategoryDTO;
import com.project.ShoesProject.entity.Category;
import com.project.ShoesProject.response.UpdateCategoryResponse;
import com.project.ShoesProject.service.Impl.CategoryService;
import com.project.ShoesProject.utils.LocalizationUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.LocaleResolver;

import java.util.List;
import java.util.Locale;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/categories")
public class CategoryController {
    private final CategoryService categoryService;
    private final LocaleResolver localeResolver;
    private final MessageSource messageSource;
    private final LocalizationUtils localizationUtils;

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
    public ResponseEntity<UpdateCategoryResponse> update(@Valid @RequestBody CategoryDTO categoryDTO, @PathVariable Long id,
    HttpServletRequest request){
        try {
            categoryService.update(categoryDTO,id);
            Locale locale = localeResolver.resolveLocale(request);
            return ResponseEntity.ok().body(UpdateCategoryResponse.builder()
                            .message(messageSource.getMessage("category.update_category.update_successfully", null, locale))
                    .build());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(UpdateCategoryResponse.builder()
                            .message(e.getMessage())
                    .build());
        }
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
        categoryService.delete(id);
    }
}
