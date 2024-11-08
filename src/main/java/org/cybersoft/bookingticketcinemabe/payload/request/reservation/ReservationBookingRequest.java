package org.cybersoft.bookingticketcinemabe.payload.request.reservation;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;
import java.util.List;

public record ReservationBookingRequest(
        @NotEmpty(message = "The seatIds must not empty")
        List<@Positive(message = "ALL seatIds must be positive")
                Integer> seatIds,
        LocalDateTime timeReservation
) {
}
