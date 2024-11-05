package org.cybersoft.bookingticketcinemabe.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.cybersoft.bookingticketcinemabe.enums.Status;
import org.cybersoft.bookingticketcinemabe.exception.runtime.AuthenticateException;
import org.cybersoft.bookingticketcinemabe.exception.runtime.CinemaNotFoundException;
import org.cybersoft.bookingticketcinemabe.exception.runtime.NotFoundColumnException;
import org.cybersoft.bookingticketcinemabe.exception.runtime.NotValidException;
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
    @ExceptionHandler(value = {NotFoundException.class})
    ResponseEntity<?> handlingUserException(NotFoundException exception) {
        return new ResponseEntity<>(BaseResponse.builder()
                .message(exception.getMessage())
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .build(), HttpStatus.OK);
    }

    @ExceptionHandler(value = {ExistedException.class})
    ResponseEntity<?> handlingUserException(ExistedException exception) {
        return new ResponseEntity<>(BaseResponse.builder()
                .message(exception.getMessage())
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .build(), HttpStatus.OK);
    }

    @ExceptionHandler(value = {BadRequestException.class})
    ResponseEntity<?> handlingUserException(BadRequestException exception) {
        return new ResponseEntity<>(BaseResponse.builder()
                .message(exception.getMessage())
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .build(), HttpStatus.OK);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        String errorMessage = (exception.getFieldError() != null)
                ? exception.getFieldError()
                .getDefaultMessage()
                : "Validation error";
        return new ResponseEntity<>(BaseResponse.builder()
                .message(errorMessage)
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {
            CinemaNotFoundException.class,
            NotFoundColumnException.class,
            org.cybersoft.bookingticketcinemabe.exception.runtime.NotFoundException.class
    })
    public ResponseEntity<Object> handleNotFoundException(RuntimeException runtimeException, HttpServletRequest request) {
        ErrorResponse<Object> errorResponse = this.createExceptionResponse(
                runtimeException,
                request,
                HttpStatus.NOT_FOUND,
                runtimeException.getMessage(),
                runtimeException.getMessage()
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {
            NotValidException.class
    })
    public ResponseEntity<Object> handleNotValidException(RuntimeException runtimeException, HttpServletRequest request) {
        ErrorResponse<Object> errorResponse = this.createExceptionResponse(
                runtimeException,
                request,
                HttpStatus.BAD_REQUEST,
                runtimeException.getCause()
                        .getMessage(),
                runtimeException.getCause()
                        .getMessage()
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {
            org.cybersoft.bookingticketcinemabe.exception.runtime.BadRequestException.class
    })
    public ResponseEntity<Object> handleBadRequestException(RuntimeException runtimeException, HttpServletRequest request) {
        ErrorResponse<Object> errorResponse = this.createExceptionResponse(
                runtimeException,
                request,
                HttpStatus.BAD_REQUEST,
                runtimeException.getMessage(),
                runtimeException.getMessage()
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {
            AuthenticateException.class
    })
    public ResponseEntity<Object> handleAuthenticateException(AuthenticateException authenticateException, HttpServletRequest request) {
        ErrorResponse<Object> errorResponse = this.createExceptionResponse(
                authenticateException,
                request,
                HttpStatus.FORBIDDEN,
                "Authentication Error",
                authenticateException.getMessage()
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }

    /**
     * Help generate error exception response
     *
     * @param runtimeException Provide the runtime exception
     * @param request          Provide the httpServletRequest
     * @param httpStatus       Provide the HttpStatus enum code
     * @return ErrorResponse
     */
    public ErrorResponse<Object> createExceptionResponse(RuntimeException runtimeException, HttpServletRequest request, HttpStatus httpStatus, String message, Object details) {
        return ErrorResponse.builder()
                .status(Status.ERROR.toString()
                        .toLowerCase())
                .statusCode(httpStatus.getReasonPhrase())
                .error(
                        ErrorDetailResponse.builder()
                                .code(httpStatus.getReasonPhrase())
                                .message(message)
                                .details(details)
                                .timestamp(LocalDateTime.now())
                                .path(request.getRequestURI())
                                .build()
                )
                .build();
    }
}
