package com.maintaining.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "be online to chose a car.")
public class OfflineDriverExceptoin extends Exception {
    public OfflineDriverExceptoin(String msge) {
        super(msge);
    }
}
