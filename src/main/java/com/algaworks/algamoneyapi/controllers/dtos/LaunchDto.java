package com.algaworks.algamoneyapi.controllers.dtos;

import com.algaworks.algamoneyapi.entities.enums.LaunchType;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

public record LaunchDto (
     Long id,
     String description,
     @JsonFormat(pattern =  "dd/MM/yyyy")
     LocalDate expirationDate,
     @JsonFormat(pattern =  "dd/MM/yyyy")
     LocalDate paymentDate,
     BigDecimal value,
     String note,
     LaunchType type,
     CategoryDto category,
     PersonDto person
) { }
