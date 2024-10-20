package org.cybersoft.bookingticketcinemabe.payload.request;

import java.time.LocalDateTime;
import java.util.List;

public record ScreeningCreationRequest(
        LocalDateTime startTime,
        Integer hallId,
        List<Integer> hallIds,
        Integer movieId
) {
}
