package com.josedacruz.learning.spring.backend_server.services.message;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Map;

//@Primary
@Service
public class EmailService implements MessageService {
    @Override
    public void sendMessage(String message, Map<String, String> parameters) {
        System.out.println("Sending email to " + parameters.get("to") + " with message: " + message + " and subject: " + parameters.get("subject"));
    }
}
