package com.thoughtworks.capacity.gtb.mvc;

public class UserExistedException extends RuntimeException {
    public UserExistedException(String message) {
        super(message);
    }
}
