package org.cybersoft.bookingticketcinemabe.payload.request.user;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import org.cybersoft.bookingticketcinemabe.annotation.validator.ValidPassword;
import org.cybersoft.bookingticketcinemabe.annotation.validator.ValueOfEnum;
import org.cybersoft.bookingticketcinemabe.enums.Roles;

@Builder
public record UserCreationRequest(
        Boolean isEmailVerified,
        Boolean isPhoneVerified,
        String phone,
        @ValueOfEnum(enumClass = Roles.class)
        String role,
        @Email(message = "The email address is invalid and cannot be registered")
        @NotEmpty(message = "Email is required")
        String email,
        String firstName,
        String lastName,
        String fullName,
        String avatar,
        @ValidPassword
        @NotEmpty(message = "The password must not empty")
        String password
) {
}

