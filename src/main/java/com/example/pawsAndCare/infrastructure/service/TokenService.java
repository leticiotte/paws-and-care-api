package com.example.pawsAndCare.infrastructure.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.pawsAndCare.domain.model.Admin;
import com.example.pawsAndCare.domain.model.response.SigninResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;


@Service
public class TokenService {
    public ResponseEntity<SigninResponse> generateToken(Admin admin) {
        SigninResponse signinResponse = new SigninResponse();
        signinResponse.setAdminId(admin.getId());
        Instant expirationTime = Instant.now().plus(24, ChronoUnit.HOURS);

        String token = JWT.create()
                .withIssuer("Admin")
                .withSubject(admin.getEmail())
                .withClaim("id", admin.getId())
                .withExpiresAt(expirationTime)
                .sign(Algorithm.HMAC256("secret"));
        signinResponse.setToken(token);

        return ResponseEntity.ok().body(signinResponse);
    }

    public String getSubject(String token) {
        return JWT
                .require(Algorithm.HMAC256("secret"))
                .withIssuer("Admin")
                .build()
                .verify(token)
                .getSubject();
    }
}
