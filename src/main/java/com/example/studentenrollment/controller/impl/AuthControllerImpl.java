package com.example.studentenrollment.controller.impl;

import com.example.studentenrollment.controller.AuthController;
import com.example.studentenrollment.dto.request.LoginRequestDTO;
import com.example.studentenrollment.dto.response.AuthResponseDTO;
import com.example.studentenrollment.model.User;
import com.example.studentenrollment.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthControllerImpl implements AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    @Override
    @PostMapping
    public ResponseEntity<AuthResponseDTO> authenticate(@RequestBody @Valid LoginRequestDTO loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtUtils.generateJwtToken(authentication);

        User currentUser = (User) authentication.getPrincipal();

        return ResponseEntity.ok(new AuthResponseDTO(
                jwt,
                currentUser.getUserType(),
                currentUser.getUsername(),
                currentUser.getId()));
    }
}
