package com.algaworks.algamoneyapi.repositories.launch;

import com.algaworks.algamoneyapi.entities.Launch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface LaunchRepositoryQuery {
    Page<Launch> filter(LaunchFilter filter, Pageable pageable);
}
