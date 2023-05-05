package com.example.studentenrollment.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity(name = "Course")
@Table(name="Course")
@EqualsAndHashCode
@Getter
@Setter
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private int year;

    @Column(nullable = false)
    private int semester;

    @Column(nullable = false)
    private int maxStudents;

    @Column(nullable = false)
    private int creditsAmount;

    @Column(nullable = false, unique = true)
    private int courseCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JoinColumn(table="Classroom", name = "id", nullable = false)
    private Classroom classroom;




}
