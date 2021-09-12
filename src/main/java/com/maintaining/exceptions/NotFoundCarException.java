package com.maintaining.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "there is no car with this id.")
public class NotFoundCarException extends RuntimeException {
    static final long serialVersionUID = -374649329787229948L;
    public NotFoundCarException(String msge) {
        super(msge);
    }
}
