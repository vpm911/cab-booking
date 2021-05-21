package com.cabs.app.aop;

import com.cabs.app.exceptions.ClientErrorException;
import com.cabs.app.exceptions.ServerErrorException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandlerAdvice {

    @ExceptionHandler(value = {ClientErrorException.class})
    protected ResponseEntity<Object> handleClientException(ClientErrorException ex) {
        return ResponseEntity.status(ex.getStatus()).body(ex.getMessage());
    }

    @ExceptionHandler(value = {ServerErrorException.class})
    protected ResponseEntity<Object> handleServerException(ServerErrorException ex) {
        return ResponseEntity.status(ex.getStatus()).body(ex.getMessage());
    }
}
