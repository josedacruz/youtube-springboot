package com.josedacruz.learning.spring.backend_server.security;

import com.josedacruz.learning.spring.backend_server.domain.User;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
@Scope("prototype")
public class PasswordResetToken {

    private static final Logger logger = LoggerFactory.getLogger(PasswordResetToken.class);

    private final String token;
    private final LocalDateTime expiration;
    private String email;

    public PasswordResetToken() {
        this.token = UUID.randomUUID().toString();
        this.expiration = LocalDateTime.now().plusMinutes(15);
    }

    public void assignToUser(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public LocalDateTime getExpiration() {
        return expiration;
    }

    public String getEmail() {
        return email;
    }

}

