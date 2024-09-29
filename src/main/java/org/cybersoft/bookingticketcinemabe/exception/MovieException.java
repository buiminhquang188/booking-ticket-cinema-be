package org.cybersoft.bookingticketcinemabe.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MovieException extends RuntimeException {
    private String message;
}
