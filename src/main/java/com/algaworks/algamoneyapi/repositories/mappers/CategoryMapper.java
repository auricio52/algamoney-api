package com.algaworks.algamoneyapi.repositories.mappers;

import com.algaworks.algamoneyapi.entities.Category;
import com.algaworks.algamoneyapi.repositories.dtos.CategoryDto;

public class CategoryMapper {
    public static CategoryDto toCategoryDto(Category category) {
        return new CategoryDto(category.getId(), category.getName());
    }

    public static Category fromCategoryDto(CategoryDto categoryDto) {
        Category category = new Category();
        category.setId(categoryDto.id());
        category.setName(categoryDto.name());
        return category;
    }
}
