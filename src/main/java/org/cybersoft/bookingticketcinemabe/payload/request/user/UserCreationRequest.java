package org.cybersoft.bookingticketcinemabe.payload.request.user;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.cybersoft.bookingticketcinemabe.annotation.validator.ValidPassword;

@Builder
public record UserCreationRequest(


        Boolean isEmailVerified,
        Boolean isPhoneVerified,
        String phone,

        String role,
        @Email(message = "The email address is invalid and cannot be registered")
        @NotEmpty(message = "Email must not empty")
        @NotNull(message = "Email is required")
        String email,

        String firstName,

        String lastName,

        String fullName,

        String avatar,

        @ValidPassword
        String password
) {
}

