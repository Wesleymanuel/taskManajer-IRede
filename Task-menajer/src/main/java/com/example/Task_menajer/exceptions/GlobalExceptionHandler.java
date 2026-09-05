package com.example.Task_menajer.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<UserNotFoundExceptionDTO> handleUserNotFoundException(UserNotFoundException e) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        UserNotFoundExceptionDTO error = new UserNotFoundExceptionDTO(status.value(), e.getMessage());
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(TaskAlreadyExistException.class)
    public ResponseEntity<TaskAlreadyExistExceptionDTO> handleTaskAlreadyExistException(Exception e) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        TaskAlreadyExistExceptionDTO error = new TaskAlreadyExistExceptionDTO(status.value(), e.getMessage());
        return ResponseEntity.status(status).body(error);
    }
}
