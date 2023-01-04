package com.algaworks.algamoneyapi.repositories;

import com.algaworks.algamoneyapi.entities.Launch;
import com.algaworks.algamoneyapi.repositories.launch.LaunchRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LaunchRepository extends JpaRepository<Launch, Long>, LaunchRepositoryQuery { }
