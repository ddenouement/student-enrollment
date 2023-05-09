package com.example.studentenrollment.validator;

import com.example.studentenrollment.repository.FacultyRepository;
import com.example.studentenrollment.validator.annotation.ExistingFacultyId;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ExistingFacultyIdValidator implements ConstraintValidator<ExistingFacultyId, Integer> {
    @Autowired
    FacultyRepository facultyRepository;

    @Override
    public void initialize(ExistingFacultyId existingFacultyId) {

    }

    @Override
    public boolean isValid(Integer id, ConstraintValidatorContext constraintValidatorContext) {
        //todo introduce cache
        return facultyRepository.findById(Long.valueOf(id)).isPresent();
    }
}
