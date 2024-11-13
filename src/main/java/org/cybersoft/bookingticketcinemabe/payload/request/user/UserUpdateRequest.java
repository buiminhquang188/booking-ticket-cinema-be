package org.cybersoft.bookingticketcinemabe.payload.request.user;

import lombok.Builder;
import org.cybersoft.bookingticketcinemabe.annotation.validator.ValidPassword;
import org.cybersoft.bookingticketcinemabe.annotation.validator.ValueOfEnum;
import org.cybersoft.bookingticketcinemabe.enums.Roles;

@Builder
public record UserUpdateRequest(
        String phone,
        @ValueOfEnum(enumClass = Roles.class)
        String role,
        String firstName,
        String lastName,
        String fullName,
        String avatar,
        @ValidPassword
        String password
) {
}
