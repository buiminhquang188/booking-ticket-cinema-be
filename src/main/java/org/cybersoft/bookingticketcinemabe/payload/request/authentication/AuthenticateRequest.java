package org.cybersoft.bookingticketcinemabe.payload.request.authentication;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record AuthenticateRequest(
        @Email(message = "This must be an email")
        @NotEmpty(message = "This field is not empty")
        String email,
        @NotEmpty(message = "This field is not empty")
        String password) {

}
