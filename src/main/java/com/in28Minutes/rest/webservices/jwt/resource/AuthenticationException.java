package com.in28Minutes.rest.webservices.jwt.resource;
public class AuthenticationException extends RuntimeException {
    public AuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }
}

