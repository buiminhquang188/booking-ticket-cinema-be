package org.cybersoft.bookingticketcinemabe.payload.request.screening;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public record ScreeningCreationRequest(
        @Future(message = " The start time must be a future date")
        LocalDateTime startTime,
        @NotEmpty(message = "The hallId must not empty")
        @Positive(message = "The hallId must be positive")
        @Digits(integer = 10, fraction = 0, message = "The hallId must be an integer")
        Integer hallId,
        @NotEmpty(message = "The movieId must not empty")
        @Positive(message = "The movieId must be positive")
        @Digits(integer = 10, fraction = 0, message = "The movieId must be an integer")
        Integer movieId
) {
}
