package com.garage.garage.handler;

import com.garage.garage.entities.Message;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;

@RestControllerAdvice
public class MyControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Message> handleMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException) {
    return  new ResponseEntity<Message>(new Message("400","Validation Error","Invalid Credentials"), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Message> handleEntityNotFoundException(EntityNotFoundException entityNotFoundException) {
        return  new ResponseEntity<Message>(new Message("404","No Match Found","Not Found"), HttpStatus.NOT_FOUND);
    }

}
