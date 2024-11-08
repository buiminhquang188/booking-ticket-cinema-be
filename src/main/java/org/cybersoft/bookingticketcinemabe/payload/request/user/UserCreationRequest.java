package org.cybersoft.bookingticketcinemabe.payload.request.user;


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
        @NotNull(message = "The Email verified must not null")
        Boolean isEmailVerified,

        @NotNull(message = "The Phone verified must not null")
        Boolean isPhoneVerified,
        String phone,
        @ValueOfEnum(enumClass = Roles.class)
        String role,
        @ValueOfEnum(enumClass = Roles.class)
        List<String> roles,
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

