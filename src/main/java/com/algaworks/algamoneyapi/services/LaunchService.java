package com.algaworks.algamoneyapi.services;

import com.algaworks.algamoneyapi.entities.Launch;
import com.algaworks.algamoneyapi.repositories.LaunchRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LaunchService {
    private LaunchRepository launchRepository;

    public LaunchService(LaunchRepository launchRepository) {
        this.launchRepository = launchRepository;
    }

    public List<Launch> list() {
        return launchRepository.findAll();
    }

    public Optional<Launch> findById(Long id) {
        return launchRepository.findById(id);
    }
}
