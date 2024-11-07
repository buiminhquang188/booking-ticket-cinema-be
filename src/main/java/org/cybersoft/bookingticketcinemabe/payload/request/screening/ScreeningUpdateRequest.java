package org.cybersoft.bookingticketcinemabe.payload.request.screening;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import org.cybersoft.bookingticketcinemabe.annotation.validator.ValidSeats;

import java.time.LocalDateTime;
import java.util.List;

public record ScreeningUpdateRequest(
        @Future(message = " The start time must be a future date")
        LocalDateTime startTime,
        @NotEmpty(message = "The time must not empty")
        @Positive(message = "The hall Id must be positive")
        @Digits(integer = 10, fraction = 0, message = "The hall Id must be an integer")
        Integer hallId,
        @NotEmpty(message = "The movie Id must not empty")
        @Positive(message = "The movie Id must be positive")
        @Digits(integer = 10, fraction = 0, message = "The movie Id must be an integer")
        Integer movieId,
        @ValidSeats
        List<ScreeningUpdateSeat> screeningSeats
) {
}
