package org.cybersoft.bookingticketcinemabe.payload.request.screening;

import jakarta.validation.constraints.Future;

import java.time.LocalDateTime;

public record ScreeningCreationRequest(
        @Future(message = " The start time must be a future date")
        LocalDateTime startTime,
        Integer hallId,
        Integer movieId
) {
}
