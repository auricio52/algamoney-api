package com.algaworks.algamoneyapi.repositories.dtos;

public record AddressDto (
     String publicPlace,
     String number,
     String complement,
     String district,
     String zipCode,
     String city,
     String state
) { }
