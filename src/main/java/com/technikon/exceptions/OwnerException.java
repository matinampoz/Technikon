package com.technikon.exceptions;

public class OwnerException extends Exception {
    
    public OwnerException() {
        super("!!!PROPERTY OWNER EXCEPTION!!!");
    }

    public OwnerException(String message) {
        super(message);
    }
    
}
