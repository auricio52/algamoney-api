package com.algaworks.algamoneyapi.controllers;

import com.algaworks.algamoneyapi.controllers.dtos.LaunchDto;
import com.algaworks.algamoneyapi.controllers.mappers.LaunchMapper;
import com.algaworks.algamoneyapi.entities.Launch;
import com.algaworks.algamoneyapi.repositories.launch.LaunchFilter;
import com.algaworks.algamoneyapi.services.LaunchService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
@RestController
@RequestMapping("/launches")
public class LaunchController {
    private LaunchService launchService;

    public LaunchController(LaunchService launchService) {
        this.launchService = launchService;
    }

    @GetMapping
    public Page<LaunchDto> list(LaunchFilter filter, Pageable pageable) {
        Page<Launch> launches = launchService.filter(filter, pageable);
        return launches.map(LaunchMapper::toLaunchDto);
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

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        launchService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
