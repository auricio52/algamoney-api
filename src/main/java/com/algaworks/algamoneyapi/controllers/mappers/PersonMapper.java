package com.algaworks.algamoneyapi.controllers.mappers;

import com.algaworks.algamoneyapi.entities.Address;
import com.algaworks.algamoneyapi.entities.Person;
import com.algaworks.algamoneyapi.controllers.dtos.AddressDto;
import com.algaworks.algamoneyapi.controllers.dtos.PersonDto;

public class PersonMapper {
    public static PersonDto toPersonDto(Person person) {
        AddressDto addressDto = null;
        if (person.getAddress() != null) {
            addressDto = AddressMapper.toAddressDto(person.getAddress());
        }
        PersonDto personDto = new PersonDto(
            person.getId(),
            person.getName(),
            person.getActive(),
            addressDto
        );
        return personDto;
    }

    public static Person fromPersonDto(PersonDto personDto) {
        Person person = new Person();
        person.setId(personDto.id());
        person.setName(personDto.name());
        person.setActive(personDto.active());
        if (personDto.address() != null) {
            Address address = AddressMapper.fromAddressDto(personDto.address());
            person.setAddress(address);
        }
        return person;
    }
}
