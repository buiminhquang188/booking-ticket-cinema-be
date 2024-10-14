package org.cybersoft.bookingticketcinemabe.payload.request;

public record HallCreationRequest(
        String name,
        Integer totalSeats,
        Integer branchId
) {
}
