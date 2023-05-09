package com.example.studentenrollment.error;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.Map;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@AllArgsConstructor
public class ApiErrorResponse {
    private int statusCode;
    private String message;
    private String path;
    private long timestamp;
    private Map<String, String> validationErrors;

    public ApiErrorResponse(final int statusCode, final String message, final String path){
        this.timestamp = new Date().getTime();
        this.statusCode = statusCode;
        this.message = message;
        this.path = path;
    }

}
