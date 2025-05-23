package com.josedacruz.learning.spring.backend_server.rest.testing;

import com.josedacruz.learning.spring.backend_server.repositories.EntitiesRepository;
import com.josedacruz.learning.spring.backend_server.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ConditionalOnProperty(name="tests.admin.enabled", havingValue="true")
public class TestRestController {

    private final UsersRepository usersRepository;
    private final EntitiesRepository entitiesRepository;

    // For educational purposes only, this is not a good practice
    // if you want to follow a good structure, use the services!!!
    @Autowired
    public TestRestController(UsersRepository usersRepository, EntitiesRepository entitiesRepository) {
        this.usersRepository = usersRepository;
        this.entitiesRepository = entitiesRepository;
    }

    @DeleteMapping("/tests/admin/users/reset")
    public void resetUsers() {
        usersRepository.resetUsers();
    }

    @DeleteMapping("/tests/admin/entities/reset")
    public void resetEntities() {
        entitiesRepository.resetEntities();
    }

}
