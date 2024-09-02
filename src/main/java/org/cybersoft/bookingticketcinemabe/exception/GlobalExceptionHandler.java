package org.cybersoft.bookingticketcinemabe.exception;

import org.cybersoft.bookingticketcinemabe.payload.response.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = UserException.class)
    ResponseEntity<?> handlingUserException (UserException exception) {
        return new ResponseEntity<>(BaseResponse.builder().message(exception.getMessage()).statusCode(500).build(), HttpStatus.OK);
    }
}
