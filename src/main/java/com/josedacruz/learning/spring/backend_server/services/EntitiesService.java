package com.josedacruz.learning.spring.backend_server.services;

import com.josedacruz.learning.spring.backend_server.domain.Category;
import com.josedacruz.learning.spring.backend_server.domain.Entity;
import com.josedacruz.learning.spring.backend_server.observability.RequestScopedTraceId;
import com.josedacruz.learning.spring.backend_server.repositories.EntitiesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EntitiesService {

    private static final Logger logger = LoggerFactory.getLogger(EntitiesService.class);

    private final EntitiesRepository entitiesRepository;
    private final RequestScopedTraceId requestScopedTraceId;

    @Autowired
    public EntitiesService(EntitiesRepository entitiesRepository,
                           RequestScopedTraceId requestScopedTraceId) {
        this.entitiesRepository = entitiesRepository;
        this.requestScopedTraceId = requestScopedTraceId;
    }

    public Optional<Entity> getEntityById(int id) {
        return entitiesRepository.findById(id);
    }

    public List<Entity> getEntities() {
        System.out.println("RequestScopedTraceId: " + requestScopedTraceId.getTraceId());
        return entitiesRepository.findAll();
    }

    // The code below is for educational purposes only. (transactional)
    public void insertEntityCategoryWithFail_WithoutTransaction() {
        Entity entity = new Entity();
        entity.setName("Test Entity0");
        entitiesRepository.save(entity);

        if(entity != null)    {
            // Simulating an error that would cause the transaction to fail
            throw new RuntimeException("Simulated failure after saving entity");
        }

        Category category = new Category();
        category.setName("Test Category0");
        category.setType("INCOME");
        categoriesService.save(category);
    }


    @Autowired
    private CategoryService categoriesService;

    @Transactional
    public void insertEntityCategoryWithFail_WithAnnotation() {
        Entity entity = new Entity();
        entity.setName("Test Entity1");
        entitiesRepository.save(entity);

        if(entity != null)    {
            // Simulating an error that would cause the transaction to fail
            throw new RuntimeException("Simulated failure after saving entity");
        }

        Category category = new Category();
        category.setName("Test Category1");
        category.setType("INCOME");
        categoriesService.save(category);
    }

    @Autowired
    private PlatformTransactionManager transactionManager;

    public void insertEntityCategoryWithFail_WithoutAnnotation() {
        DefaultTransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
        TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);

        try {
            Entity entity = new Entity();
            entity.setName("Test Entity2");
            entitiesRepository.save(entity);

            if(entity != null)    {
                // Simulating an error that would cause the transaction to fail
                throw new RuntimeException("Simulated failure after saving entity");
            }

            Category category = new Category();
            category.setName("Test Category2");
            category.setType("INCOME");
            categoriesService.save(category);
            transactionManager.commit(transactionStatus);
        } catch (Exception e) {
            logger.error("Error occurred while inserting entity and category", e);
            transactionManager.rollback(transactionStatus);
            throw e; // rethrow the exception to indicate failure
        }
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
