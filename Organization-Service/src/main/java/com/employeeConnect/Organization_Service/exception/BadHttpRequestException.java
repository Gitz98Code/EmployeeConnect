package com.employeeConnect.Organization_Service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadHttpRequestException extends RuntimeException {
    public BadHttpRequestException(String message){
        super(message);
    }
}
