package com.josedacruz.learning.spring.backend_server.rest;

import com.josedacruz.learning.spring.backend_server.telemetry.TelemetryCollector;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class TelemetryRestController {

    private final TelemetryCollector telemetry;

    public TelemetryRestController(TelemetryCollector telemetry) {
        this.telemetry = telemetry;
    }

    @GetMapping("/telemetry")
    public Map<String, Long> telemetry() {
        return telemetry.snapshot();
    }
}
