package org.cybersoft.bookingticketcinemabe.payload.request.movie;

import jakarta.validation.constraints.*;

import java.time.LocalDateTime;
import java.util.List;

public record MovieCreationRequest(
        @Positive(message = "The rating must be positive")
        @Digits(integer = 3, fraction = 1, message = "The rating must be a valid monetary amount with up to 3 integer digits and 1 decimal digits")
        Byte rating,
        @NotNull(message = "The time must not null")
        @Positive(message = "The time must be positive")
        Integer time,
        @Future(message = " The start time must be a future date")
        LocalDateTime startDate,

        @NotEmpty(message = "The name must not empty")
        String name,
        String poster,
        String trailer,
        List<@Positive(message = "ALL branchIds must be positive")
                Integer> branchIds,
        List<@Positive(message = "ALL screeningIds must be positive")
                Integer> screeningIds) {
}
