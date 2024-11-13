package org.cybersoft.bookingticketcinemabe.payload.request.user;

import lombok.Builder;
import org.cybersoft.bookingticketcinemabe.annotation.validator.ValidPassword;

@Builder
public record ProfileUpdateRequest(
        String phone,
        String firstName,
        String lastName,
        String fullName,
        String avatar,
        @ValidPassword
        String password
) {
}
