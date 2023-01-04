package com.algaworks.algamoneyapi.repositories.launch;

import com.algaworks.algamoneyapi.entities.Launch;

import java.util.List;

public interface LaunchRepositoryQuery {
    public List<Launch> filter(LaunchFilter filter);
}
