package org.cybersoft.bookingticketcinemabe.exception.runtime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticateException extends RuntimeException {
    private String message;
}