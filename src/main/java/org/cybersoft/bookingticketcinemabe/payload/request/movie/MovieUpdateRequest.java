package org.cybersoft.bookingticketcinemabe.payload.request.movie;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;
import java.util.List;

public record MovieUpdateRequest(
        @Positive(message = "The rating must be positive")
        @Digits(integer = 3, fraction = 1, message = "The rating must be a valid monetary amount with up to 3 integer digits and 1 decimal digits")
        Byte rating,

        @Positive(message = "The time must be positive")
        Integer time,
        @Future(message = "The start time must be a future date")
        LocalDateTime startDate,

        String name,
        String poster,
        String trailer,
        @Valid
        List<@Positive(message = "ALL branchIds must be positive")
                Integer> branchIds,
        @Valid
        List<@Positive(message = "All screeningIds must be positive")
                Integer> screeningIds) {
}
