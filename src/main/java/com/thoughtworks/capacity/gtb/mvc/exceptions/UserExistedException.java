package com.thoughtworks.capacity.gtb.mvc.exceptions;

public class UserExistedException extends RuntimeException {
    public UserExistedException(String message) {
        super(message);
    }
}
