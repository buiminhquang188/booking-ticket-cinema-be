package org.cybersoft.bookingticketcinemabe.payload.request.movie;

import jakarta.validation.constraints.Future;

import java.time.LocalDateTime;
import java.util.List;

public record MovieUpdateRequest(
        Byte rating,
        Integer time,
        @Future(message = "The start time must be a future date")
        LocalDateTime startDate,

        String name,
        String poster,
        String trailer,
        List<Integer> branchIds,
        List<Integer> screeningIds) {
}
