package com.example.studentenrollment.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentResponseSimpleDTO {
    private long id;
    private String firstName;
    private String lastName;
    private String address;
    private FacultyResponseSimpleDTO faculty;
    private int age;
    private int yearOfStudy;
}
