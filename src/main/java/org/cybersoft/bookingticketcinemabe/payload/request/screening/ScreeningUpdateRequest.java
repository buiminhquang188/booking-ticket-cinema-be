package org.cybersoft.bookingticketcinemabe.payload.request.screening;

import java.time.LocalDateTime;
import java.util.List;

public record ScreeningUpdateRequest(
        LocalDateTime startTime,
        Integer movieId,
        List<Integer> reservationIds,
        List<Integer> seatReservationIds
) {
}
