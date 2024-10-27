package org.cybersoft.bookingticketcinemabe.payload.request.screening;

import java.time.LocalDateTime;

public record ScreeningCreationRequest(
        LocalDateTime startTime,
        Integer hallId,
        Integer movieId
) {
}
