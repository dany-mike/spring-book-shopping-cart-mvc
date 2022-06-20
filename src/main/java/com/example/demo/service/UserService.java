package com.example.demo.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.demo.controllers.dto.UserRegistrationDto;
import com.example.demo.models.User;

public interface UserService extends UserDetailsService {
    User save(UserRegistrationDto registrationDto);

    // User getUser();
}