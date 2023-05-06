package com.example.studentenrollment.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
@Getter
@Setter
public class EnrollmentCompositeKey implements Serializable {

    @Column(name = "student_id")
    private long studentId;

    @Column(name = "course_id")
    private long courseId;
}
