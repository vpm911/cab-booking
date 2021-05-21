package com.cabs.app.exceptions;

import org.springframework.http.HttpStatus;

public class ServerErrorException extends RuntimeException {
    HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

    public ServerErrorException(){
        super();
    }

    public ServerErrorException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public ServerErrorException(String message) {
        super(message);
    }
}
