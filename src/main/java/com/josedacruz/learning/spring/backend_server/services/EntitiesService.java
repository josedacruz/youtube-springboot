package com.josedacruz.learning.spring.backend_server.services;

import com.josedacruz.learning.spring.backend_server.domain.Entity;
import com.josedacruz.learning.spring.backend_server.repositories.EntitiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EntitiesService {

    private final EntitiesRepository entitiesRepository;

    @Autowired
    public EntitiesService(EntitiesRepository entitiesRepository) {
        this.entitiesRepository = entitiesRepository;
    }

    public Optional<Entity> getEntityById(int id) {
        return entitiesRepository.findById(id);
    }
}
