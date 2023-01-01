package com.algaworks.algamoneyapi.repositories.dtos;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record CategoryDto(Long id, @NotNull @Length(min = 3, max = 50) String name) { }
