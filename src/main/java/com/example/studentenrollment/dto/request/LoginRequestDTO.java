package com.example.studentenrollment.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class LoginRequestDTO {
    @NotNull
    private String email;
    @NotNull
    private String password;
}
