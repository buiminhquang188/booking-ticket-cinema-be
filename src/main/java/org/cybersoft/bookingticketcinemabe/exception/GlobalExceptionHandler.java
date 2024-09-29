package org.cybersoft.bookingticketcinemabe.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.cybersoft.bookingticketcinemabe.enums.Status;
import org.cybersoft.bookingticketcinemabe.exception.runtime.CinemaNotFoundException;
import org.cybersoft.bookingticketcinemabe.payload.response.BaseResponse;
import org.cybersoft.bookingticketcinemabe.payload.response.ErrorDetailResponse;
import org.cybersoft.bookingticketcinemabe.payload.response.ErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {UserException.class,
            BranchException.class,
            MovieException.class})
    ResponseEntity<?> handlingUserException(UserException exception) {
        return new ResponseEntity<>(BaseResponse.builder().message(exception.getMessage()).statusCode(HttpStatus.BAD_REQUEST.value()).build(), HttpStatus.OK);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        String errorMessage = (exception.getFieldError() != null)
                ? exception.getFieldError().getDefaultMessage()
                : "Validation error";
        return new ResponseEntity<>(BaseResponse.builder().message(errorMessage).statusCode(HttpStatus.BAD_REQUEST.value()).build(), HttpStatus.OK);
    }

    @ExceptionHandler(value = {
            CinemaNotFoundException.class
    })
    public ResponseEntity<Object> handleNotFoundException(RuntimeException runtimeException, HttpServletRequest request) {
        ErrorResponse<Object> errorResponse = ErrorResponse.builder()
                .status(Status.ERROR.toString()
                        .toLowerCase())
                .statusCode(HttpStatus.NOT_FOUND.getReasonPhrase())
                .error(
                        ErrorDetailResponse.builder()
                                .code(HttpStatus.NOT_FOUND.getReasonPhrase())
                                .message(runtimeException.getMessage())
                                .details(runtimeException.getMessage())
                                .timestamp(LocalDateTime.now())
                                .path(request.getRequestURI())
                                .build()
                )
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
