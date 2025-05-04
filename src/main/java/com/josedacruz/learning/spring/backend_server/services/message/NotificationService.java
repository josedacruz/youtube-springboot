package com.josedacruz.learning.spring.backend_server.services.message;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class NotificationService implements MessageService {
    @Override
    public void sendMessage(String message, Map<String, String> parameters) {
        System.out.println("Sending notification to " + parameters.get("to") + " with message: " + message);
    }
}
