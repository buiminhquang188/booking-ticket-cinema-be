package org.cybersoft.bookingticketcinemabe.payload.request.user;

import lombok.Builder;
import org.cybersoft.bookingticketcinemabe.annotation.validator.OnlyOneOf;
import org.cybersoft.bookingticketcinemabe.annotation.validator.ValidPassword;
import org.cybersoft.bookingticketcinemabe.annotation.validator.ValueOfEnum;
import org.cybersoft.bookingticketcinemabe.enums.Roles;

import java.util.List;

@OnlyOneOf(fields = {"role", "roles"})
@Builder
public record UserUpdateRequest(
        String phone,
        @ValueOfEnum(enumClass = Roles.class)
        String role,
        @ValueOfEnum(enumClass = Roles.class)
        List<String> roles,
        String firstName,
        String lastName,
        String fullName,
        String avatar,
        @ValidPassword
        String password
) {
}
