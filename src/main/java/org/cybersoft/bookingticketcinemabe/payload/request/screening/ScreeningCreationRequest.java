package org.cybersoft.bookingticketcinemabe.payload.request.screening;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public record ScreeningCreationRequest(
        @Future(message = " The start time must be a future date")
        LocalDateTime startTime,
        @NotNull(message = "The hallId must not null")
        @Positive(message = "The hallId must be positive")
        Integer hallId,
        @NotNull(message = "The movieId must not null")
        @Positive(message = "The movieId must be positive")
        Integer movieId
) {
}
