package org.cybersoft.bookingticketcinemabe.payload.request.authentication;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record AuthenticateRequest(
        @Email(message = "The email address is invalid and cannot be authenticated")
        @NotEmpty(message = "Email is required")
        String email,
        @NotEmpty(message = "This field is not empty")
        String password) {

}
