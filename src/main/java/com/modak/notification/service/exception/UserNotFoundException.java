package com.modak.notification.service.exception;

public class UserNotFoundException extends IllegalArgumentException {
    public UserNotFoundException() {
        super("User not found exception");
    }
}
