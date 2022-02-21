package com.genius.tokenservice.exception;

/**
 * This exception is throwed when user not found
 */
public class ApplicationUserNotFoundException extends RuntimeException {

    /**
     * Constructor of the excepiton
     * 
     * @param message
     */
    public ApplicationUserNotFoundException(String message) {
        super(message);
    }

}
