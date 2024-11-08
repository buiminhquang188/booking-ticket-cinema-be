package org.cybersoft.bookingticketcinemabe.payload.request.screening;

import org.cybersoft.bookingticketcinemabe.annotation.validator.ValidSeats;
import org.cybersoft.bookingticketcinemabe.enums.ScreeningStatus;

import java.time.LocalDateTime;
import java.util.List;

public record ScreeningUpdateRequest(
        LocalDateTime startTime,
        Integer hallId,
        Integer movieId,
        ScreeningStatus status,
        @ValidSeats
        List<ScreeningUpdateSeat> screeningSeats
) {
}
