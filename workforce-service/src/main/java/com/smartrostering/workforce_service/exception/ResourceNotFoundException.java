package com.smartrostering.workforce_service.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String what, Object id) { super(what + " " + id + " not found"); }
    
}
