package com.example.pawsAndCare.domain.exception;

public class PetNotFoundException extends Exception{
    public PetNotFoundException(String message) {
        super(message);
    }
}
