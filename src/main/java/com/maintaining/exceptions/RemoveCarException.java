package com.maintaining.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "You have no car to deselect it !  .")
public class RemoveCarException extends RuntimeException{
    static final long serialVersionUID = -374649329787229948L;
    public RemoveCarException(String msge) {
        super(msge);
    }
}
