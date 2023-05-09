package com.example.studentenrollment.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AuthResponseDTO {
    private String jwtToken;
    private String userType;
    private String userName;
    private long userId;
}
