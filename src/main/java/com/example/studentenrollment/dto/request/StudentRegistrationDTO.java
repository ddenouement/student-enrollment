package com.example.studentenrollment.dto.request;

import com.example.studentenrollment.validator.annotation.ExistingFacultyId;
import com.example.studentenrollment.validator.annotation.UniqueEmail;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@AllArgsConstructor
@Setter
@Getter
@Builder
public class StudentRegistrationDTO {
    @NotNull
    @Email(regexp = ".+[@].+[\\.].+")
    @UniqueEmail
    private String email;
    @NotNull
    private String password;
    @NotNull
    @Size(min = 2, max = 10)
    private String firstName;
    @NotNull
    @Size(min = 2, max = 10)
    private String lastName;
    @NotNull
    @ExistingFacultyId
    private int facultyId;
    @NotNull
    private int yearOfStudy;

    @Size(min = 2, max = 100)
    private String address;
    @NotNull
    private LocalDate dateOfBirth;

}
