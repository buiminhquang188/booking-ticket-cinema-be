package org.cybersoft.bookingticketcinemabe.exception.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.http.HttpServletRequest;
import org.cybersoft.bookingticketcinemabe.exception.GlobalExceptionHandler;
import org.cybersoft.bookingticketcinemabe.payload.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class JwtExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {
            SignatureException.class
    })
    public ResponseEntity<Object> handleSignatureException(SignatureException signatureException, HttpServletRequest request) {
        ErrorResponse<Object> errorResponse = new GlobalExceptionHandler().createExceptionResponse(
                signatureException,
                request,
                HttpStatus.FORBIDDEN,
                signatureException.getMessage(),
                "Invalid token"
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = {
            ExpiredJwtException.class
    })
    public ResponseEntity<Object> handleExpiredJwtException(ExpiredJwtException expiredJwtException, HttpServletRequest request) {
        ErrorResponse<Object> errorResponse = new GlobalExceptionHandler().createExceptionResponse(
                expiredJwtException,
                request,
                HttpStatus.FORBIDDEN,
                expiredJwtException.getMessage(),
                "Expired token"
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = {
            MalformedJwtException.class
    })
    public ResponseEntity<Object> handleMalformedJwtException(MalformedJwtException malformedJwtException, HttpServletRequest request) {
        ErrorResponse<Object> errorResponse = new GlobalExceptionHandler().createExceptionResponse(
                malformedJwtException,
                request,
                HttpStatus.FORBIDDEN,
                malformedJwtException.getMessage(),
                "Malformed token"
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = {
            UnsupportedJwtException.class
    })
    public ResponseEntity<Object> handleUnsupportedJwtException(UnsupportedJwtException unsupportedJwtException, HttpServletRequest request) {
        ErrorResponse<Object> errorResponse = new GlobalExceptionHandler().createExceptionResponse(
                unsupportedJwtException,
                request,
                HttpStatus.FORBIDDEN,
                unsupportedJwtException.getMessage(),
                "Unsupported token"
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }

}
