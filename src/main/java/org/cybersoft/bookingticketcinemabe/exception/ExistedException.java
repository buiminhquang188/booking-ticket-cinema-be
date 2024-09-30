package org.cybersoft.bookingticketcinemabe.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExistedException extends RuntimeException {
    private String message;
}
