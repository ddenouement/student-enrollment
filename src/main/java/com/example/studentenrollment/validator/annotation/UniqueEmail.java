package com.example.studentenrollment.validator.annotation;

import com.example.studentenrollment.validator.UniqueEmailValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(
        validatedBy = {UniqueEmailValidator.class}
)
public @interface UniqueEmail {
    String message() default  "Email provided already exists";
    public Class<?>[] groups() default {};
    public Class<? extends Payload>[] payload() default {};

}
