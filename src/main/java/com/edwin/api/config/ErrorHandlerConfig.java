package com.edwin.api.config;

import com.edwin.api.exception.DataExistsException;
import com.edwin.api.exception.DataNotFoundException;
import com.edwin.api.exception.GeneralServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ErrorHandlerConfig extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> all(Exception e, WebRequest request){
        return new ResponseEntity<>(new CustomResponse<>(false, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(GeneralServiceException.class)
    public ResponseEntity<?> all(GeneralServiceException e, WebRequest request){
        return new ResponseEntity<>(new CustomResponse<>(false, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<?> all(DataNotFoundException e, WebRequest request){
        return new ResponseEntity<>(new CustomResponse<>(false, e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataExistsException.class)
    public ResponseEntity<?> all(DataExistsException e, WebRequest request){
        return new ResponseEntity<>(new CustomResponse<>(false, e.getMessage()), HttpStatus.ACCEPTED);
    }
}