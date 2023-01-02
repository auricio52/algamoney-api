package com.algaworks.algamoneyapi.repositories;

import com.algaworks.algamoneyapi.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
