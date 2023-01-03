package com.algaworks.algamoneyapi.controllers.mappers;

import com.algaworks.algamoneyapi.controllers.dtos.CategoryDto;
import com.algaworks.algamoneyapi.controllers.dtos.LaunchDto;
import com.algaworks.algamoneyapi.controllers.dtos.PersonDto;
import com.algaworks.algamoneyapi.entities.Category;
import com.algaworks.algamoneyapi.entities.Launch;
import com.algaworks.algamoneyapi.entities.Person;

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

    public static Launch fromLaunchDto(LaunchDto launchDto) {
        Person person = PersonMapper.fromPersonDto(launchDto.person());
        Category category = CategoryMapper.fromCategoryDto(launchDto.category());
        Launch launch = new Launch();
        launch.setId(launchDto.id());
        launch.setDescription(launchDto.description());
        launch.setNote(launchDto.note());
        launch.setExpirationDate(launchDto.expirationDate());
        launch.setPaymentDate(launchDto.paymentDate());
        launch.setType(launchDto.type());
        launch.setValue(launchDto.value());
        launch.setCategory(category);
        launch.setPerson(person);
        return launch;
    }
}
