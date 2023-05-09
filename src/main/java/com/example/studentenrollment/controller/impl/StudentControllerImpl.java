package com.example.studentenrollment.controller.impl;

import com.example.studentenrollment.controller.StudentController;
import com.example.studentenrollment.dto.request.StudentRegistrationDTO;
import com.example.studentenrollment.dto.response.FacultyResponseSimpleDTO;
import com.example.studentenrollment.dto.response.StudentResponseSimpleDTO;
import com.example.studentenrollment.model.Student;
import com.example.studentenrollment.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/students")
public class StudentControllerImpl implements StudentController {

    @Autowired
    StudentService studentService;

    @Override
    @PostMapping
    public ResponseEntity<StudentResponseSimpleDTO> registerStudent(@RequestBody @Valid StudentRegistrationDTO student) {
        Student savedEntity = studentService.registerStudent(student);
        //todo introduce mapping via MapStruct
        FacultyResponseSimpleDTO facultyDTO = FacultyResponseSimpleDTO.builder()
                .id((int) savedEntity.getFaculty().getId())
                .name(savedEntity.getFaculty().getName())
                .build();
        StudentResponseSimpleDTO response = StudentResponseSimpleDTO.builder()
                .address(savedEntity.getAddress())
                .age(savedEntity.getAge())
                .firstName(savedEntity.getFirstName())
                .lastName(savedEntity.getLastName())
                .id(savedEntity.getId())
                .yearOfStudy(savedEntity.getYearOfStudy())
                .faculty(facultyDTO)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
