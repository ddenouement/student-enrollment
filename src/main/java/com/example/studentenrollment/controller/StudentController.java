package com.example.studentenrollment.controller;

import com.example.studentenrollment.dto.request.StudentRegistrationDTO;
import com.example.studentenrollment.dto.response.StudentResponseSimpleDTO;
import org.springframework.http.ResponseEntity;

public interface StudentController {
    ResponseEntity<StudentResponseSimpleDTO> registerStudent(StudentRegistrationDTO student);
}
