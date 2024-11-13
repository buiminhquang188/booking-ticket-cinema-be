package org.cybersoft.bookingticketcinemabe.exception.runtime;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}
