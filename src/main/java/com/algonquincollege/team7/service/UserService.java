package com.algonquincollege.team7.service;

import com.algonquincollege.team7.dto.SignupRequest;
import com.algonquincollege.team7.infra.exception.ApiException;
import com.algonquincollege.team7.model.User;
import com.algonquincollege.team7.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    public UserService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public void signupUser(SignupRequest data) {
        var user = new User(data);

        var duplicatedUser = userRepository.existsByEmail(data.email());
        if (duplicatedUser) {
            throw new ApiException(HttpStatus.CONFLICT, "User already registered");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
}
