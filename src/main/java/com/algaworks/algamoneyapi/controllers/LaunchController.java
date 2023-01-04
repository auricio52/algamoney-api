package com.algaworks.algamoneyapi.controllers;

import com.algaworks.algamoneyapi.controllers.dtos.LaunchDto;
import com.algaworks.algamoneyapi.controllers.mappers.LaunchMapper;
import com.algaworks.algamoneyapi.entities.Launch;
import com.algaworks.algamoneyapi.services.LaunchService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
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

    @PostMapping
    public ResponseEntity<LaunchDto> create(@Valid @RequestBody LaunchDto launchDto) {
        Launch launch = launchService.create(LaunchMapper.fromLaunchDto(launchDto));
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(launch.getId()).toUri();
        return ResponseEntity.created(uri).body(LaunchMapper.toLaunchDto(launch));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LaunchDto> findById(@PathVariable Long id) {
        Launch launch = launchService.findById(id);
        return ResponseEntity.ok(LaunchMapper.toLaunchDto(launch));
    }
}
