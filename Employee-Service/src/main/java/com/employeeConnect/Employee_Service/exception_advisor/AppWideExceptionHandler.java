package com.employeeConnect.Employee_Service.exception_advisor;


import com.employeeConnect.Employee_Service.exception.*;
import com.employeeConnect.Employee_Service.utill.StandardResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class AppWideExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<StandardResponse> handleNotFoundException(NotFoundException e){
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(404,"Error",e.getMessage()), HttpStatus.NOT_FOUND);

    }
    @ExceptionHandler(ValidateException.class)
    public ResponseEntity<StandardResponse>handleValidateException(ValidateException e){

        return new ResponseEntity<StandardResponse>(
                new StandardResponse(400,"Error",e.getMessage()),HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(DuplicateEntryException.class)
    public ResponseEntity<StandardResponse>handleDuplicateEntryException(DuplicateEntryException e){

        return new ResponseEntity<StandardResponse>(
                new StandardResponse(409,"Error",e.getMessage()),HttpStatus.CONFLICT);
    }
    @ExceptionHandler(GlobalException.class)
    public ResponseEntity<StandardResponse>handleGlobalException(GlobalException e){

        return new ResponseEntity<StandardResponse>(
                new StandardResponse(500,"Error",e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(AlreadyExistException.class)
    public ResponseEntity<StandardResponse>handleAlreadyExistsException(AlreadyExistException e){

        return new ResponseEntity<StandardResponse>(
                new StandardResponse(500,"Error",e.getMessage()),HttpStatus.CONFLICT);
    }
    @ExceptionHandler(BadHttpRequestException.class)
    public ResponseEntity<StandardResponse> handleBadHttpRequestException(BadHttpRequestException e) {

        return new ResponseEntity<StandardResponse>(
                new StandardResponse(400, "Bad Request", e.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
