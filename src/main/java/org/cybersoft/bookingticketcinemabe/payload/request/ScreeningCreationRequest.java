package org.cybersoft.bookingticketcinemabe.payload.request;

import java.time.LocalDateTime;

public record ScreeningCreationRequest(
        LocalDateTime startTime,
        Integer hallId,
        Integer movieId
) {
}
