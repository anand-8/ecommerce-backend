package com.anand.ecommerce_backend.controller;

import com.anand.ecommerce_backend.entity.Category;
import com.anand.ecommerce_backend.service.CategoryService;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @PostMapping
    public Category createCategory(@RequestBody Category category){
        return categoryService.createCategory(category);

    }
    @GetMapping
    public List<Category> getAllCategories(){
        return categoryService.getAllCategories();
    }

}
