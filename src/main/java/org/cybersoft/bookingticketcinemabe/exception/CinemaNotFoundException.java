package org.cybersoft.bookingticketcinemabe.exception;

public class CinemaNotFoundException extends RuntimeException {
    public CinemaNotFoundException(Integer id) {
        super("The cinema id '" + id + "' does not found");
    }
}
