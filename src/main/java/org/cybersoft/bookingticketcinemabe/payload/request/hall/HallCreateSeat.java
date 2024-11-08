package org.cybersoft.bookingticketcinemabe.payload.request.hall;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record HallCreateSeat(
        @Positive(message = "The seatColumn must be positive")
        @Digits(integer = 10, fraction = 0, message = "The seatColumn must be an integer")
        Integer seatColumn,
        String seatRow,
        @Positive(message = "The seatNumber must be positive")
        @Digits(integer = 10, fraction = 0, message = "The seatNumber must be an integer")
        Integer seatNumber,
        String seatType,
        String seatPrice,

        @NotNull(message = "The isActive must not null")
        Boolean isActive
) {
}
