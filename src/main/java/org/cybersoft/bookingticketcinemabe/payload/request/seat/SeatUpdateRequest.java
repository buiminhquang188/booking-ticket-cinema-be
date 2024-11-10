package org.cybersoft.bookingticketcinemabe.payload.request.seat;

public record SeatUpdateRequest(
        Integer seatTypeId,
        Double seatPrice,
        Boolean isActive
) {
}
