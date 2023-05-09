package com.example.studentenrollment.validator;

import com.example.studentenrollment.repository.UserRepository;
import com.example.studentenrollment.validator.annotation.UniqueEmail;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {
    @Autowired
    UserRepository userRepository;

    @Override
    public void initialize(UniqueEmail uniqueEmail) {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return userRepository.findByEmail(s).isPresent();
    }
}
