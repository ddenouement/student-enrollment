package com.example.studentenrollment.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.util.*;

@EqualsAndHashCode
@Entity(name = "Student")
@Table
@Getter
@Setter
public class Student extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false)
    private long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = true)
    private String address;

    @Column(nullable = false)
    private int yearOfStudy;

    @Column(nullable = false)
    private LocalDate dateOfBirth;

    @Transient
    @Formula("TIMESTAMPDIFF(YEAR,BIRTHDATE,CURDATE())")
    private int age;

    @OneToMany(mappedBy = "student")
    private Set<Enrollment> enrollments = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JoinColumn(name = "fk_faculty_id", nullable = false)
    private Faculty faculty;


    @Builder(builderMethodName = "studentBuilder")
    Student(String firstName, String lastName, String email, String password, long id,
            int yearOfStudy, LocalDate dateOfBirth, Faculty faculty){
        super(email, password, id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.faculty = faculty;
        this.yearOfStudy = yearOfStudy;
    }

    public static class StudentBuilder {

        private List<Authority> authorities = Arrays.asList(
                Authority.ENROLL_TO_COURSE,
                Authority.READ_STUDENTS_ENROLLED_TO_COURSE
                );
    }

   /* public Student(long id, String hashedPassword, String email, List<Authority> authorities) {
        super(id, hashedPassword, email, authorities);
    }*/
}
