package com.algaworks.algamoneyapi.controllers;

import com.algaworks.algamoneyapi.entities.Category;
import com.algaworks.algamoneyapi.repositories.dtos.CategoryDto;
import com.algaworks.algamoneyapi.repositories.mappers.CategoryMapper;
import com.algaworks.algamoneyapi.services.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<CategoryDto> list() {
        List<Category> categories = categoryService.list();
        return categories.stream().map(CategoryMapper::toCategoryDto).collect(Collectors.toList());
    }
}
