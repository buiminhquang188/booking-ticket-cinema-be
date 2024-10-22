package org.cybersoft.bookingticketcinemabe.payload.request;


import jakarta.validation.constraints.Email;
import lombok.Builder;

@Builder
public record UserCreationRequest(


        Boolean isEmailVerified,
        Boolean isPhoneVerified,
        String phone,

        String role,
        @Email(message = "The email address is invalid and cannot be registered")
        String email,

        String firstName,

        String lastName,

        String fullName,

        String avatar,

        String password
) {
}

