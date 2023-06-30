package com.example.pawsAndCare.application.controller;

import com.example.pawsAndCare.domain.model.Admin;
import com.example.pawsAndCare.domain.model.request.SigninRequest;
import com.example.pawsAndCare.domain.model.response.SigninResponse;
import com.example.pawsAndCare.domain.utils.PasswordUtils;
import com.example.pawsAndCare.infrastructure.service.AdminService;
import com.example.pawsAndCare.infrastructure.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/signin")
public class SigninController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping
    public ResponseEntity<SigninResponse> signin(@Valid @RequestBody SigninRequest signinRequest){
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(
                        signinRequest.getEmail(), signinRequest.getPassword()
                );

        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        var admin = (Admin) authentication.getPrincipal();

        return tokenService.generateToken(admin);
    }
}
