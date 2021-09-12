package com.maintaining.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "someone using this car now try later or choose other car.")
public class CarAlreadyInUseException extends Exception {
    private static final long serialVersionUID = -687991492884005033L;
    public CarAlreadyInUseException(String message) {
        super(message);
    }

}
