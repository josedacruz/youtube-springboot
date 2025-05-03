package com.josedacruz.learning.spring.backend_server.configurations;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class MyHealthIndicator implements HealthIndicator {

    @Override
    public Health health() {
        boolean isServiceUp = checkComponents();

        if (isServiceUp) {
            return Health.up()
                    .withDetail("message", "Finances service is running smoothly.")
                    .withDetail("Accounts verified", 42)
                    .build();
        } else {
            return Health.down()
                    .withDetail("error", "The database seems unreachable.")
                    .withDetail("Accounts verified", 0)
                    .withDetail("suggestion", "Check the cloud database connection.")
                    .build();
        }
    }

    private boolean checkComponents() {
        return Math.random() > 0.2; // 80% chance the service is healthy
    }
}