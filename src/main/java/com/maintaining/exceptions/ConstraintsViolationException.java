package com.maintaining.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Some constraints are violated .")
public class ConstraintsViolationException extends Exception {
    private static final long serialVersionUID = -687991492884005033L;
    public ConstraintsViolationException(String message) {
        super(message);
    }
}
