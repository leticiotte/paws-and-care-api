package com.example.pawsAndCare.domain.model.security;

import com.example.pawsAndCare.domain.repository.AdminRepository;
import com.example.pawsAndCare.infrastructure.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class FilterToken extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AdminRepository adminRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token;
        var authorization = request.getHeader("Authorization");

        if (authorization != null){
            token = authorization.replace("Bearer ", "");
            var subject = this.tokenService.getSubject(token);

            var admin = this.adminRepository.findByEmail(subject);

            var authentication = new UsernamePasswordAuthenticationToken(admin, null, admin.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }
}
