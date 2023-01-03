package com.algaworks.algamoneyapi.controllers.mappers;

import com.algaworks.algamoneyapi.controllers.dtos.CategoryDto;
import com.algaworks.algamoneyapi.controllers.dtos.LaunchDto;
import com.algaworks.algamoneyapi.controllers.dtos.PersonDto;
import com.algaworks.algamoneyapi.entities.Launch;

public class LaunchMapper {
    public static LaunchDto toLaunchDto(Launch launch) {
        CategoryDto categoryDto = CategoryMapper.toCategoryDto(launch.getCategory());
        PersonDto personDto = PersonMapper.toPersonDto(launch.getPerson());
        LaunchDto launchDto = new LaunchDto(
            launch.getId(),
            launch.getDescription(),
            launch.getExpirationDate(),
            launch.getPaymentDate(),
            launch.getValue(),
            launch.getNote(),
            launch.getType(),
            categoryDto,
            personDto
        );
        return launchDto;
    }
}
