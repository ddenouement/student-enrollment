package com.example.studentenrollment.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table
@EqualsAndHashCode
@Setter
@Getter
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String title;

    @Column(unique = true)
    private String email;




    @ManyToOne
    Faculty faculty;

    @ManyToMany
    Set<Course> curatedCourses;

}
