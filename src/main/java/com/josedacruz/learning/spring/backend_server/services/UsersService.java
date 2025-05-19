package com.josedacruz.learning.spring.backend_server.services;

import com.josedacruz.learning.spring.backend_server.repositories.UsersRepository;
import com.josedacruz.learning.spring.backend_server.domain.User;
import com.josedacruz.learning.spring.backend_server.security.PasswordResetToken;
import com.josedacruz.learning.spring.backend_server.services.message.MessageService;
import com.josedacruz.learning.spring.backend_server.telemetry.TelemetryCollector;
import com.josedacruz.learning.spring.backend_server.telemetry.TelemetryUserAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Lazy
public class UsersService {

    private static final Logger logger = LoggerFactory.getLogger(UsersService.class);

    private final UsersRepository usersRepository;
    private final MessageService emailService;
    private final MessageService notificationService;
    private final TelemetryCollector telemetryCollector;
    private final PasswordService passwordService;

    @Autowired
    public UsersService(
            UsersRepository usersRepository,
            @Qualifier("emailService") MessageService emailService,
            @Qualifier("notificationService") MessageService notificationService,
            TelemetryCollector telemetryCollector,
            PasswordService passwordService) {

        this.usersRepository = usersRepository;
        this.emailService = emailService;
        this.notificationService = notificationService;
        this.telemetryCollector = telemetryCollector;
        this.passwordService = passwordService;
        logger.info("UsersService initialized");
    }

    public Optional<User> getUserById(int id) {
        return usersRepository.getUserById(id);
    }

    public Optional<User> getUserByUsername(String username) {
        return usersRepository.getUserByUsername(username);
    }

    public List<User> getUsers() {
        return usersRepository.getUsers();
    }

    public User createUser(User user) {

        var newUser =  usersRepository.save(user);
        if(newUser != null) {
            telemetryCollector.recordUserAction(TelemetryUserAction.CREATED);
            emailService.sendMessage(
                    "Hello " + user.getName() + ", welcome to the financial system. ",
                    Map.of(
                            "to", user.getEmail(),
                            "subject", "Welcome to the financial system"
                    ));
        }
        return newUser;
    }

    public Optional<User> updateUser(int id, User user) {
        User existingUser = this.getUserById(id).orElse(null);
        if(existingUser == null) {
            return Optional.empty();
        }

        existingUser.setUsername(user.getUsername());
        existingUser.setPassword(user.getPassword());
        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        existingUser.setDepartment(user.getDepartment());
        var updUser = Optional.of(usersRepository.update(existingUser));

        telemetryCollector.recordUserAction(TelemetryUserAction.UPDATED);
        notificationService.sendMessage(
                "Hello " + user.getName() + ", your account has been updated. ",
                Map.of(
                        "to", user.getEmail()
                ));
        return updUser;
    }

    public void deleteUser(int id) {
        Optional<User> user = this.getUserById(id);
        if(user.isPresent()) {
            usersRepository.delete(user.get());
            telemetryCollector.recordUserAction(TelemetryUserAction.DELETED);
        } else {
            throw new RuntimeException("User not found");
        }
    }

    public Integer countUsers() {
        return usersRepository.countUsers();
    }

    public Map<String, List<String>> getUsersByDepartment() {
        return usersRepository.getUsersByDepartment();
    }

    public PasswordResetToken resetPassword(String username) {
        Optional<User> user = usersRepository.getUserByUsername(username);
        return passwordService.generateTokenFor(user.get());
    }

    public List<String> getPasswordTokens() {
        return usersRepository.getPasswordTokens();
    }
}
