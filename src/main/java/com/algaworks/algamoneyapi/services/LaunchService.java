package com.algaworks.algamoneyapi.services;

import com.algaworks.algamoneyapi.entities.Launch;
import com.algaworks.algamoneyapi.entities.Person;
import com.algaworks.algamoneyapi.exceptions.InactivePersonException;
import com.algaworks.algamoneyapi.exceptions.ResourceNotFoundException;
import com.algaworks.algamoneyapi.repositories.LaunchRepository;
import com.algaworks.algamoneyapi.repositories.launch.LaunchFilter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LaunchService {
    private LaunchRepository launchRepository;
    private PersonService personService;

    public LaunchService(LaunchRepository launchRepository, PersonService personService) {
        this.launchRepository = launchRepository;
        this.personService = personService;
    }

    public List<Launch> list() {
        return launchRepository.findAll();
    }

    public List<Launch> filter(LaunchFilter filter) {
        return launchRepository.filter(filter);
    }

    public Launch create(Launch launch) {
        Person person = personService.findById(launch.getPerson().getId());
        if (!person.getActive()) {
            throw new InactivePersonException();
        }
        return launchRepository.save(launch);
    }

    public Launch findById(Long id) {
        return launchRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Launch not found!"));
    }

    public void deleteById(Long id) {
        launchRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Launch not found!"));
        launchRepository.deleteById(id);
    }
}
