package org.cybersoft.bookingticketcinemabe.payload.request.user;

import lombok.Builder;

@Builder
public record UserUpdateRequest(
        Integer id,
        String phone,
        String role,
        String firstName,
        String lastName,
        String fullName,
        String avatar,
        String password
) {
}
