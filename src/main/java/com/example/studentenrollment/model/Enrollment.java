package com.example.studentenrollment.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Table(name = "enrollment")
@Entity(name="enrollment")
@Data
public class Enrollment {

    @EmbeddedId
    private EnrollmentCompositeKey id;

    @ManyToOne
    @JoinColumn(name="course_id")
    @MapsId("courseId")
    private Course course;

    @ManyToOne
    @JoinColumn(name="student_id")
    @MapsId("studentId")
    private Student student;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "enrolled_date")
    private Date enrolledDate;
}
