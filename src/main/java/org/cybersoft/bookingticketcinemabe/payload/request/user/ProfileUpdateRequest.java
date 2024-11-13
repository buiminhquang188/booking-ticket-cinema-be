package org.cybersoft.bookingticketcinemabe.payload.request.user;

import lombok.Builder;

@Builder
public record ProfileUpdateRequest(
        String phone,
        String firstName,
        String lastName,
        String fullName,
        String avatar
) {
}
