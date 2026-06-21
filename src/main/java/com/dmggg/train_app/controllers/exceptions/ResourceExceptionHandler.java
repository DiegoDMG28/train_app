package com.dmggg.train_app.controllers.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.dmggg.train_app.services.exceptions.DatabaseException;
import com.dmggg.train_app.services.exceptions.EntityNotFound;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {

  @ExceptionHandler(EntityNotFound.class)
  public ResponseEntity<StandartError> entityNotFound(EntityNotFound e, HttpServletRequest request){
    HttpStatus status = HttpStatus.NOT_FOUND;
    StandartError err = new StandartError();
    err.setTimestamp(Instant.now());
    err.setStatus(status.value());
    err.setError("resource not found");
    err.setMessage(e.getMessage());
    err.setPath(request.getRequestURI());

    return ResponseEntity.status(status).body(err);
  }

  @ExceptionHandler(DatabaseException.class)
  public ResponseEntity<StandartError> DatabaseException(EntityNotFound e, HttpServletRequest request){
    HttpStatus status = HttpStatus.BAD_REQUEST;
    StandartError err = new StandartError();
    err.setTimestamp(Instant.now());
    err.setStatus(status.value());
    err.setError("Database exception");
    err.setMessage(e.getMessage());
    err.setPath(request.getRequestURI());

    return ResponseEntity.status(status).body(err);
  }

}

