package com.example.Task_menajer.exceptions;

public class TaskAlreadyExistException extends RuntimeException {
    public TaskAlreadyExistException(String message) {
        super(message);
    }
}
