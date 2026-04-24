package com.cts.blogapp.exceptiion;

import com.cts.blogapp.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    //exception handle method
    @ExceptionHandler(value = { ResourceNotFoundException.class })
    public ResponseEntity<ApiResponse> handleException(ResourceNotFoundException e) {
        return  new ResponseEntity<>(ApiResponse.create(e.getMessage(), HttpStatus.NOT_FOUND.value(),HttpStatus.NOT_FOUND.name()), HttpStatus.NOT_FOUND);
    }
}
