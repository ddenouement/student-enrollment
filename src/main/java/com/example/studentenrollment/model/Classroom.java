package com.example.studentenrollment.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode
@Entity(name = "Classroom")
@Table(name="Classroom")
public class Classroom {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private int numSeats;

    @Column(nullable = false, unique = true)
    private String classroomNumber;

    @OneToMany(mappedBy = "classroom")
    private Set<Course> courses = new HashSet<>();

    public long getId() {
        return id;
    }

    public String getClassroomNumber() {
        return classroomNumber;
    }

    public int getNumSeats() {
        return numSeats;
    }


    @Override
    public String toString() {
        return "Classroom{" +
                "id=" + id +
                ", numSeats=" + numSeats +
                ", classroomNumber=" + classroomNumber +
                '}';
    }
}
