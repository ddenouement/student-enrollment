package com.example.studentenrollment.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity(name = "Faculty")
@Table(name = "Faculty")
public class Faculty {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(unique = true, nullable = false)
    private String name;

    @OneToMany(mappedBy = "faculty")
    private Set<Student> students = new HashSet<>();

    @OneToMany(mappedBy = "faculty")
    private Set<Course> courses = new HashSet<>();

    @OneToMany(mappedBy = "faculty")
    private Set<Teacher> teachers = new HashSet<>();

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Faculty{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
