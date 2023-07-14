package com.example.usermanagement.exception;

public class UserNotFoundException extends Exception {

    private final String id;

    public UserNotFoundException(String id) {
        super("User not found with id: " + id);
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
