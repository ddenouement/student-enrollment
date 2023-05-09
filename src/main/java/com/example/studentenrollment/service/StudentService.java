package com.example.studentenrollment.service;

import com.example.studentenrollment.dto.request.StudentRegistrationDTO;
import com.example.studentenrollment.model.Student;

public interface StudentService {
   Student registerStudent(StudentRegistrationDTO studentRegistrationDTO);
}
