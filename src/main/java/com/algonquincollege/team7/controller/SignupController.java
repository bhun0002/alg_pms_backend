package com.algonquincollege.team7.controller;

import com.algonquincollege.team7.dto.SignupRequest;
import com.algonquincollege.team7.dto.SignupResponse;
import com.algonquincollege.team7.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/signup")
public class SignupController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity signupUser(@RequestBody @Valid SignupRequest data) {
        userService.signupUser(data);
        var responseOk = new SignupResponse(data.email(), "User registered successfully");
        return ResponseEntity.ok(responseOk);
    }
}
