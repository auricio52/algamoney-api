package com.algaworks.algamoneyapi.services;

import com.algaworks.algamoneyapi.entities.Person;
import com.algaworks.algamoneyapi.exceptions.ResourceNotFoundException;
import com.algaworks.algamoneyapi.repositories.PersonRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    private PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> list() {
        return personRepository.findAll();
    }

    public Person create(Person person) {
        return personRepository.save(person);
    }

    public Optional<Person> findById(Long id) {
        return personRepository.findById(id);
    }

    public Person update(Long id, Person person) {
        Person savedPerson = personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Person not found"));
        BeanUtils.copyProperties(person, savedPerson, "id");
        return personRepository.save(savedPerson);
    }

    public void deleteById(Long id) {
        personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Person not found"));
        personRepository.deleteById(id);
    }
}
