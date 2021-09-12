package com.maintaining.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "there is no car with this id.")
public class NotFoundDriverExpetion extends Exception{
    static final long serialVersionUID = -234767896674289948L;
    public NotFoundDriverExpetion(String msge) {
        super(msge);
    }
}
