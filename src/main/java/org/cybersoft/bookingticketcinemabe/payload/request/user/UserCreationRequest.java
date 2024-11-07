package org.cybersoft.bookingticketcinemabe.payload.request.user;


import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.cybersoft.bookingticketcinemabe.annotation.validator.OnlyOneOf;
import org.cybersoft.bookingticketcinemabe.annotation.validator.ValidPassword;
import org.cybersoft.bookingticketcinemabe.annotation.validator.ValueOfEnum;
import org.cybersoft.bookingticketcinemabe.enums.Roles;

import java.util.List;

@Builder
@OnlyOneOf(fields = {"role", "roles"})
public record UserCreationRequest(
        @AssertTrue(message = "Email must be verified")
        Boolean isEmailVerified,
        Boolean isPhoneVerified,
        String phone,
        @ValueOfEnum(enumClass = Roles.class)
        String role,
        @ValueOfEnum(enumClass = Roles.class)
        List<String> roles,
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

