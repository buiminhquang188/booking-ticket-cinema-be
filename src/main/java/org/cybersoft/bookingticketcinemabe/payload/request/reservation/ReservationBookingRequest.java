package org.cybersoft.bookingticketcinemabe.payload.request.reservation;

import java.time.LocalDateTime;
import java.util.List;

public record ReservationBookingRequest(
        List<Integer> seatIds,
        LocalDateTime timeReservation
) {
}
