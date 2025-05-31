package com.josedacruz.learning.spring.backend_server.rest;

import com.josedacruz.learning.spring.backend_server.domain.Entity;
import com.josedacruz.learning.spring.backend_server.services.EntitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EntitiesRestController {

    private final EntitiesService entitiesService;

    @Autowired
    public EntitiesRestController(EntitiesService entitiesService) {
        this.entitiesService = entitiesService;
    }

    @GetMapping("/entities")
    public ResponseEntity<List<Entity>> getEntities() {
        List<Entity> entities = entitiesService.getEntities();
        return ResponseEntity.ok(entities);
    }

    @GetMapping("/entities/{id}")
    public ResponseEntity<Entity> getEntityById(@PathVariable("id") int id) {
        return entitiesService.getEntityById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/entities")
    public ResponseEntity<Entity> createEntity(@RequestBody Entity entity) {
        Entity createdEntity = entitiesService.createEntity(entity);
        return ResponseEntity.status(201).body(createdEntity);
    }

    @PutMapping("/entities/{id}")
    public ResponseEntity<Entity> updateEntity(@PathVariable("id") int id, @RequestBody Entity entity) {
        entity.setId(id); // Ensure the ID is set for the update
        return entitiesService.updateEntity(entity)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/entities/{id}")
    public ResponseEntity<Void> deleteEntity(@PathVariable("id") int id) {
        if (entitiesService.deleteEntity(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
