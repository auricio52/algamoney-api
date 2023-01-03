package com.algaworks.algamoneyapi.controllers.dtos;

public record AddressDto (
     String publicPlace,
     String number,
     String complement,
     String district,
     String zipCode,
     String city,
     String state
) { }
