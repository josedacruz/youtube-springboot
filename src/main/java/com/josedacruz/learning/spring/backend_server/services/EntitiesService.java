package com.josedacruz.learning.spring.backend_server.services;

import com.josedacruz.learning.spring.backend_server.domain.Entity;
import com.josedacruz.learning.spring.backend_server.repositories.EntitiesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EntitiesService implements InitializingBean, DisposableBean {

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

    @Override
    public void destroy() throws Exception {
        logger.info("EntitiesService destroyed");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        logger.info("EntitiesService initialized");
    }
}
