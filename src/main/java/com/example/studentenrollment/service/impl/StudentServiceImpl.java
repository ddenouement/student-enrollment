package com.example.studentenrollment.service.impl;

import com.example.studentenrollment.dto.request.StudentRegistrationDTO;
import com.example.studentenrollment.error.FacultyNotFoundException;
import com.example.studentenrollment.model.Faculty;
import com.example.studentenrollment.model.Student;
import com.example.studentenrollment.repository.FacultyRepository;
import com.example.studentenrollment.repository.StudentRepository;
import com.example.studentenrollment.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    private final FacultyRepository facultyRepository;

    StudentServiceImpl(@Autowired final StudentRepository studentRepository,
                       @Autowired final FacultyRepository facultyRepository){
        this.studentRepository = studentRepository;
        this.facultyRepository = facultyRepository;
    }

    public Student registerStudent(StudentRegistrationDTO studentRegistrationDTO){
        //todo introduce cache
        Faculty faculty = facultyRepository
                .findById((long) studentRegistrationDTO.getFacultyId())
                .orElseThrow(() -> new FacultyNotFoundException());
        //todo MapStruct
        Student s =  Student.studentBuilder()
                .email(studentRegistrationDTO.getEmail())
                .password(studentRegistrationDTO.getPassword())
                .firstName(studentRegistrationDTO.getFirstName())
                .lastName(studentRegistrationDTO.getLastName())
                .dateOfBirth(studentRegistrationDTO.getDateOfBirth())
                .yearOfStudy(studentRegistrationDTO.getYearOfStudy())
                .faculty(faculty)
                .build();

        return studentRepository.save(s);
    }

}
