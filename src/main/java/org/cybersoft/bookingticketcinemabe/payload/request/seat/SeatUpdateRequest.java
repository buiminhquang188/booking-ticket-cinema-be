package org.cybersoft.bookingticketcinemabe.payload.request.seat;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

public record SeatUpdateRequest(
        @NotEmpty(message = "The seatTypeId must not empty")
        @Positive(message = "The seatTypeId must be positive")
        @Digits(integer = 10, fraction = 0, message = "The seatTypeId must be an integer")
        Integer seatTypeId,

        @Positive(message = "The seatPrice must be positive")
        Double seatPrice,
        Boolean isActive
) {
}
