package org.cybersoft.bookingticketcinemabe.payload.request.seat;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record SeatUpdateRequest(
        @NotEmpty(message = "The seatTypeId must not empty")
        @Positive(message = "The seatTypeId must be positive")
        Integer seatTypeId,

        @Positive(message = "The seatPrice must be positive")
        Double seatPrice,
        @NotNull(message = "The isActive must not null")
        Boolean isActive
) {
}
