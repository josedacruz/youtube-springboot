package com.josedacruz.learning.spring.backend_server.services;

import com.josedacruz.learning.spring.backend_server.domain.User;
import com.josedacruz.learning.spring.backend_server.repositories.UsersRepository;
import com.josedacruz.learning.spring.backend_server.security.PasswordResetToken;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.stereotype.Service;

@Service
public class PasswordService {

    private final ObjectFactory<PasswordResetToken> tokenFactory;
    private final UsersRepository usersRepository;

    public PasswordService(ObjectFactory<PasswordResetToken> tokenFactory,
                           UsersRepository usersRepository) {
        this.tokenFactory = tokenFactory;
        this.usersRepository = usersRepository;
    }

    public PasswordResetToken generateTokenFor(User user) {
        PasswordResetToken token = tokenFactory.getObject();
        token.assignToUser(user.getEmail());

        usersRepository.saveResetToken(token);

        return token;
    }
}

