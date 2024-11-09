package org.cybersoft.bookingticketcinemabe.payload.request.screening;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Positive;
import org.cybersoft.bookingticketcinemabe.annotation.validator.ValidSeats;
import org.cybersoft.bookingticketcinemabe.enums.ScreeningStatus;

import java.time.LocalDateTime;
import java.util.List;

public record ScreeningUpdateRequest(
        @Future(message = " The start time must be a future date")
        LocalDateTime startTime,
        @Positive(message = "The hall Id must be positive")
        Integer hallId,

        @Positive(message = "The movie Id must be positive")
        Integer movieId,
        ScreeningStatus status,
        @ValidSeats
        List<ScreeningUpdateSeat> screeningSeats
) {
}
