package com.algonquincollege.team7.controller;

import com.algonquincollege.team7.dto.LoginRequest;
import com.algonquincollege.team7.dto.LoginResponse;
import com.algonquincollege.team7.service.TokenService;
import com.algonquincollege.team7.model.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/login")
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity loginUser(@RequestBody @Valid LoginRequest data) {

        var token = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var authentication = authenticationManager.authenticate(token);

        var tokenJWT = tokenService.buildToken((User) authentication.getPrincipal());

        return ResponseEntity.ok(new LoginResponse(tokenJWT));
    }
}
