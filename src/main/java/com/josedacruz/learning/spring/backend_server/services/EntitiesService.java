package com.josedacruz.learning.spring.backend_server.services;

import com.josedacruz.learning.spring.backend_server.domain.Entity;
import com.josedacruz.learning.spring.backend_server.repositories.EntitiesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EntitiesService {

    private static final Logger logger = LoggerFactory.getLogger(EntitiesService.class);

    private final EntitiesRepository entitiesRepository;

    @Autowired
    public EntitiesService(EntitiesRepository entitiesRepository) {
        this.entitiesRepository = entitiesRepository;
    }

    public Optional<Entity> getEntityById(int id) {
        return entitiesRepository.findById(id);
    }

    public List<Entity> getEntities() {
        return entitiesRepository.findAll();
    }

    public Entity createEntity(Entity entity) {
        return entitiesRepository.save(entity);
    }

    public Optional<Entity> updateEntity(Entity entity) {
        entitiesRepository.update(entity);
        return entitiesRepository.findById(entity.getId());
    }

    public boolean deleteEntity(int id) {
        Optional<Entity> entity = entitiesRepository.findById(id);
        if (entity.isPresent()) {
            entitiesRepository.deleteById(id);
            return true;
        } else {
            logger.warn("Entity with id {} not found for deletion", id);
            return false;
        }
    }
}
