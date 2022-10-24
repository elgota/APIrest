package com.egg.empleado.exception;

import org.aspectj.bridge.IMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.Timestamp;
import java.time.Instant;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException e){

        ErrorResponse error = buildErrorResponse(e.getMessage(), HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    private ErrorResponse buildErrorResponse(String message, HttpStatus httpStatus){

        ErrorResponse errorResponse = new ErrorResponse();

        errorResponse.setStatus(httpStatus.value());
        errorResponse.add(message);
        errorResponse.setTimestamp(Timestamp.from(Instant.now()));

        return errorResponse;
    }
}
