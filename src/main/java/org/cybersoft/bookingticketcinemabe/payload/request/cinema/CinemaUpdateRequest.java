package org.cybersoft.bookingticketcinemabe.payload.request.cinema;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.util.List;

public record CinemaUpdateRequest(
        @NotBlank
        String name,
        String image,
        @Valid
        List<@Positive(message = "ALL provinces must be positive")
        @Digits(integer = 10, fraction = 0, message = "All provinces must be the integer")
                Integer> provinces,
        @Valid
        List<@Positive(message = "ALL branches must be positive")
        @Digits(integer = 10, fraction = 0, message = "All branches must be the integer")
                Integer> branches) {
}
