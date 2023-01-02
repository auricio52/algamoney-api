package com.algaworks.algamoneyapi.controllers;

import com.algaworks.algamoneyapi.entities.Person;
import com.algaworks.algamoneyapi.repositories.dtos.PersonDto;
import com.algaworks.algamoneyapi.repositories.mappers.PersonMapper;
import com.algaworks.algamoneyapi.services.PersonService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/persons")
public class PersonController {
    private PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public List<PersonDto> list() {
        List<Person> persons = personService.list();
        return persons.stream().map(PersonMapper::toPersonDto).collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<PersonDto> create(@Valid @RequestBody PersonDto personDto) {
        Person person = personService.create(PersonMapper.fromPersonDto(personDto));
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(person.getId()).toUri();
        return ResponseEntity.created(uri).body(PersonMapper.toPersonDto(person));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonDto> findById(@PathVariable Long id) {
        Optional<Person> optional = personService.findById(id);
        return optional.isPresent() ? ResponseEntity.ok(PersonMapper.toPersonDto(optional.get())) : ResponseEntity.notFound().build();
    }
}