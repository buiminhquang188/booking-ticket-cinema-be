package org.cybersoft.bookingticketcinemabe.payload.request;

public record HallUpdateRequest(
        String name,
        Integer totalSeats,
        Integer branchId
) {
}
