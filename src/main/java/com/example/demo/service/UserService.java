package com.example.demo.service;

import com.example.demo.service.entity.UserData;
import com.example.demo.service.entity.UserRegisterData;
import com.example.demo.service.repository.UserRepository;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class UserService {
    private final UserRepository repository;
    
    public UserService(UserRepository userRepository) {
        this.repository = userRepository;
    }

    public UserData getUserInfo(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Invalid user ID");
        }
        return repository.getUser(id);
    }

    public boolean addUser(UserRegisterData data) {
        if (data == null) {
            throw new IllegalArgumentException("User data cannot be null");
        }
        
        // Validation
        if (!isValidEmail(data.getEmail())) {
            throw new IllegalArgumentException("Invalid email format");
        }
        
        if (!isValidPassword(data.getPassword())) {
            throw new IllegalArgumentException("Password must be at least 8 characters long");
        }
        
        // Hash password
        String hashedPassword = hashPassword(data.getPassword());
        data.setPassword(hashedPassword);
        
        // Save user
        int userId = repository.addUser(data);
        return userId > 0;
    }
    
    private boolean isValidEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }
        return email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }
    
    private boolean isValidPassword(String password) {
        return password != null && password.length() >= 8;
    }
    
    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes());
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Failed to hash password", e);
        }
    }
}