package com.josedacruz.learning.spring.backend_server.services.message;

import java.util.Map;

public interface MessageService {
    void sendMessage(String message, Map<String, String> parameters);
}
