package com.example.pawsAndCare.domain.exception.handler;

import com.example.pawsAndCare.domain.exception.*;
import com.example.pawsAndCare.domain.exception.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicatedDocumentException.class)
    public ResponseEntity<Error> handleDuplicatedEmailException(DuplicatedDocumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new Error(ex.getMessage()));
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<Error> handleCustomerNotFoundException(CustomerNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new Error(ex.getMessage()));
    }

    @ExceptionHandler(CustomerDependencyException.class)
    public ResponseEntity<Error> handleCustomerDependencyException(CustomerDependencyException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new Error(ex.getMessage()));
    }

    @ExceptionHandler(PetNotFoundException.class)
    public ResponseEntity<Error> handlePetNotFoundException(PetNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new Error(ex.getMessage()));
    }

    @ExceptionHandler(SchedulingConflictException.class)
    public ResponseEntity<Error> handleSchedulingConflictException(SchedulingConflictException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new Error(ex.getMessage()));
    }
}