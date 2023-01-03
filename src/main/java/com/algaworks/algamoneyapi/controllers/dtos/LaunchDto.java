package com.algaworks.algamoneyapi.controllers.dtos;

import com.algaworks.algamoneyapi.entities.enums.LaunchType;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record LaunchDto (
     Long id,
     @NotNull
     String description,
     @NotNull
     @JsonFormat(pattern =  "dd/MM/yyyy")
     LocalDate expirationDate,
     @JsonFormat(pattern =  "dd/MM/yyyy")
     LocalDate paymentDate,
     @NotNull
     BigDecimal value,
     String note,
     @NotNull
     LaunchType type,
     @NotNull
     CategoryDto category,
     @NotNull
     PersonDto person
) { }
