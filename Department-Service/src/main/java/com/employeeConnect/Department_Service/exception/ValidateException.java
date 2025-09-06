package com.employeeConnect.Department_Service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ValidateException extends RuntimeException{
    public ValidateException(String message){
        super(message);
    }
}
