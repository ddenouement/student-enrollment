package com.example.studentenrollment.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.*;

@Entity
@Table
@EqualsAndHashCode
public class Teacher extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false)
    private long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JoinColumn(name = "fk_faculty_id", nullable = false)
    private Faculty faculty;

    @ManyToMany(mappedBy = "curatedBy")
    private Set<Course> curatedCourses;

    public Teacher(long id, String hashedPassword, String email, List<Authority> authorities) {
        super(id, hashedPassword, email, authorities);
    }


    public static class TeacherBuilder {

        private List<Authority> authorities = Arrays.asList(
                Authority.CREATE_COURSE,
                Authority.READ_STUDENTS_ENROLLED_TO_COURSE,
                Authority.READ_STUDENTS_LIST,
                Authority.READ_TEACHERS_LIST,
                Authority.MODIFY_COURSE);
        }

}
