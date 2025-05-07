package com.josedacruz.learning.spring.backend_server.telemetry;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class TelemetryCollector {

    private final AtomicLong usersCreated = new AtomicLong(0);
    private final AtomicLong usersUpdated = new AtomicLong(0);
    private final AtomicLong usersDeleted = new AtomicLong(0);

    public void recordUserAction(TelemetryUserAction userAction) {
        switch (userAction) {
            case CREATED -> usersCreated.incrementAndGet();
            case UPDATED -> usersUpdated.incrementAndGet();
            case DELETED -> usersDeleted.incrementAndGet();
        }
    }

    public Map<String, Long> snapshot() {
        return Map.of(
                "users.created", usersCreated.get(),
                "users.updated", usersUpdated.get(),
                "users.deleted", usersDeleted.get()
        );
    }
}

