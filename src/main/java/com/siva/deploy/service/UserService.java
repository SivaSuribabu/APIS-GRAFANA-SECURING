package com.siva.deploy.service;

import com.siva.deploy.model.User;
import com.siva.deploy.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        logger.info("UserService initialized with UserRepository and PasswordEncoder");
    }

    public boolean usernameExists(String username) {
        try {
            return userRepository.existsByUsername(username);
        } catch (Exception e) {
            logger.error("Error checking if username exists: " + username, e);
            return false;
        }
    }

    public User register(String username, String rawPassword) {
        try {
            String encodedPassword = passwordEncoder.encode(rawPassword);
            User user = new User(username, encodedPassword);
            User saved = userRepository.save(user);
            logger.info("User registered successfully: " + username);
            return saved;
        } catch (Exception e) {
            logger.error("Error registering user: " + username, e);
            throw new RuntimeException("Registration failed", e);
        }
    }

    public boolean authenticate(String username, String rawPassword) {
        try {
            logger.info("Attempting to authenticate user: " + username);
            return userRepository.findByUsername(username)
                    .map(user -> {
                        boolean matches = passwordEncoder.matches(rawPassword, user.getPassword());
                        logger.info("Authentication for " + username + ": " + matches);
                        return matches;
                    })
                    .orElse(false);
        } catch (Exception e) {
            logger.error("Error authenticating user: " + username, e);
            return false;
        }
    }
}
