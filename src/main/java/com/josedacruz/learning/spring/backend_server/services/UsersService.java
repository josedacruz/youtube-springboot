package com.josedacruz.learning.spring.backend_server.services;

import com.josedacruz.learning.spring.backend_server.repositories.UsersRepository;
import com.josedacruz.learning.spring.backend_server.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UsersService {

    private final UsersRepository usersRepository;

    @Autowired
    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
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

    public List<User> searchUsersFromDepartments(String department, String domain) {
        return usersRepository.searchUsersFromDepartments(department, domain);
    }

    public User createUser(User user) {
        return usersRepository.save(user);
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
        return Optional.of(usersRepository.update(existingUser));

    }

    public void deleteUser(int id) {
        Optional<User> user = this.getUserById(id);
        if(user.isPresent()) {
            usersRepository.delete(user.get());
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
}
