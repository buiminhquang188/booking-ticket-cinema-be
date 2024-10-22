package org.cybersoft.bookingticketcinemabe.payload.request;

import java.time.LocalDateTime;
import java.util.List;

public record ScreeningUpdateRequest(
        LocalDateTime startTime,
        Integer hallId,
        Integer movieId,
        List<Integer> reservationIds,
        List<Integer> seatReservationIds
) {
}
