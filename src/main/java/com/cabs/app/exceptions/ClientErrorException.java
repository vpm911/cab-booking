package com.cabs.app.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ClientErrorException extends RuntimeException{

    HttpStatus status = HttpStatus.BAD_REQUEST;

    public ClientErrorException(){
        super();
    }

    public ClientErrorException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public ClientErrorException(String message) {
        super(message);
    }
}
