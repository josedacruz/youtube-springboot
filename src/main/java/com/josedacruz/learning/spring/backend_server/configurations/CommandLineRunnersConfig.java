package com.josedacruz.learning.spring.backend_server.configurations;

import com.josedacruz.learning.spring.backend_server.domain.Entity;
import com.josedacruz.learning.spring.backend_server.services.CategoryService;
import com.josedacruz.learning.spring.backend_server.services.EntitiesService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration
public class CommandLineRunnersConfig {

    @Bean
    @Order(2)
    public CommandLineRunner newEntity(EntitiesService entiteesService ) {
        return args -> {
            System.out.println("Application started successfully (2)!");
            entiteesService.createEntity(new Entity(null, args[0]));
        };
    }

    @Bean
    @Order(1)
    public CommandLineRunner listCategories(CategoryService categoryService) {
        return args -> {
            System.out.println("Application started successfully (1)!");
            categoryService.getAllCategories().forEach(System.out::println);
        };
    }

}
