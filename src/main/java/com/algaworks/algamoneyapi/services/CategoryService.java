package com.algaworks.algamoneyapi.services;

import com.algaworks.algamoneyapi.entities.Category;
import com.algaworks.algamoneyapi.exceptions.ResourceNotFoundException;
import com.algaworks.algamoneyapi.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> list() {
        return categoryRepository.findAll();
    }

    public Category create(Category category) {
        return categoryRepository.save(category);
    }

    public Category findById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category not found!"));
    }

}
