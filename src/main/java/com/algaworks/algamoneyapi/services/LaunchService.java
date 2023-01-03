package com.algaworks.algamoneyapi.services;

import com.algaworks.algamoneyapi.entities.Launch;
import com.algaworks.algamoneyapi.exceptions.ResourceNotFoundException;
import com.algaworks.algamoneyapi.repositories.LaunchRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LaunchService {
    private LaunchRepository launchRepository;

    public LaunchService(LaunchRepository launchRepository) {
        this.launchRepository = launchRepository;
    }

    public List<Launch> list() {
        return launchRepository.findAll();
    }

    public Launch create(Launch launch) {
        return launchRepository.save(launch);
    }

    public Launch findById(Long id) {
        return launchRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Launch not found!"));
    }
}
