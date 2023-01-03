package com.algaworks.algamoneyapi.controllers;

import com.algaworks.algamoneyapi.controllers.dtos.LaunchDto;
import com.algaworks.algamoneyapi.controllers.mappers.LaunchMapper;
import com.algaworks.algamoneyapi.entities.Launch;
import com.algaworks.algamoneyapi.services.LaunchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/launches")
public class LaunchController {
    private LaunchService launchService;

    public LaunchController(LaunchService launchService) {
        this.launchService = launchService;
    }

    @GetMapping
    public List<LaunchDto> list() {
        List<Launch> launches = launchService.list();
        return launches.stream().map(LaunchMapper::toLaunchDto).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LaunchDto> findById(@PathVariable Long id) {
        Optional<Launch> optional = launchService.findById(id);
        return optional.isPresent() ? ResponseEntity.ok(LaunchMapper.toLaunchDto(optional.get())) : ResponseEntity.notFound().build();
    }
}
