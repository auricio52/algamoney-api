package com.algaworks.algamoneyapi.controllers;

import com.algaworks.algamoneyapi.entities.Category;
import com.algaworks.algamoneyapi.controllers.dtos.CategoryDto;
import com.algaworks.algamoneyapi.controllers.mappers.CategoryMapper;
import com.algaworks.algamoneyapi.services.CategoryService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

    @PostMapping
    @Transactional
    public ResponseEntity<CategoryDto> create(@Valid @RequestBody CategoryDto categoryDto) {
        Category category = categoryService.create(CategoryMapper.fromCategoryDto(categoryDto));
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(category.getId()).toUri();
        return ResponseEntity.created(uri).body(CategoryMapper.toCategoryDto(category));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> findById(@PathVariable Long id) {
        Category category = categoryService.findById(id);
        return ResponseEntity.ok(CategoryMapper.toCategoryDto(category));
    }
}
