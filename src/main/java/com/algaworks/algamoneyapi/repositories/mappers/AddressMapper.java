package com.algaworks.algamoneyapi.repositories.mappers;

import com.algaworks.algamoneyapi.entities.Address;
import com.algaworks.algamoneyapi.repositories.dtos.AddressDto;

public class AddressMapper {
    public static AddressDto toAddressDto(Address address) {
        AddressDto addressDto = new AddressDto(
            address.getPublicPlace(),
            address.getNumber(),
            address.getComplement(),
            address.getDistrict(),
            address.getZipCode(),
            address.getCity(),
            address.getState()
        );
        return addressDto;
    }

    public static Address fromAddressDto(AddressDto addressDto) {
        Address address = new Address();
        address.setCity(addressDto.city());
        address.setComplement(addressDto.complement());
        address.setNumber(addressDto.number());
        address.setDistrict(addressDto.district());
        address.setState(addressDto.state());
        address.setPublicPlace(addressDto.publicPlace());
        address.setZipCode(addressDto.zipCode());
        return address;
    }
}
