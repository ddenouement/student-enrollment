package com.example.studentenrollment.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class FacultyResponseSimpleDTO {
    private int id;
    private String name;
}
