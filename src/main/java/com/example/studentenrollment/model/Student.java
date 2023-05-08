package com.example.studentenrollment.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.util.*;

@EqualsAndHashCode
@Entity(name = "Student")
@Table
public class Student extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false)
    private long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private int yearOfStudy;

    @Column(nullable = false)
    private LocalDate dateOfBirth;

    @Transient
    @Formula("TIMESTAMPDIFF(YEAR,BIRTHDATE,CURDATE())")
    private String age;

    @OneToMany(mappedBy = "student")
    private Set<Enrollment> enrollments = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JoinColumn(name = "fk_faculty_id", nullable = false)
    private Faculty faculty;

    public static class StudentBuilder {

        private List<Authority> authorities = Arrays.asList(
                Authority.ENROLL_TO_COURSE,
                Authority.READ_STUDENTS_ENROLLED_TO_COURSE
                );
    }

    public Student(long id, String hashedPassword, String email, List<Authority> authorities) {
        super(id, hashedPassword, email, authorities);
    }
}
