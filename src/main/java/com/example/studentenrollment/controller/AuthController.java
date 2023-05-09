package com.example.studentenrollment.controller;

import com.example.studentenrollment.dto.request.LoginRequestDTO;
import org.springframework.http.ResponseEntity;

public interface AuthController {
    ResponseEntity authenticate(LoginRequestDTO loginRequestDTO);
}
