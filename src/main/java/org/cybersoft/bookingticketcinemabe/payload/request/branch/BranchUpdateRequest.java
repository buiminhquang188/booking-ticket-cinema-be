package org.cybersoft.bookingticketcinemabe.payload.request.branch;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;

public record BranchUpdateRequest(
        @NotEmpty(message = "The name must not empty")
        String name,
        String logo,
        String avatar,
        String link,
        String address,
        @Positive(message = "The cinemaId must be positive")
        @Digits(integer = 10, fraction = 0, message = "The cinemaId must be an integer")
        Integer districtId,
        @Positive(message = "The cinemaId must be positive")
        @Digits(integer = 10, fraction = 0, message = "The cinemaId must be an integer")
        Integer cinemaId,
        @Valid
        List<@Positive(message = "ALL movieIds must be positive")
        @Digits(integer = 3, fraction = 1, message = "All movieIds must be a valid monetary amount with up to 3 integer digits and 1 decimal digits")
                Integer> movieIds,
        @Positive(message = "The distance must be positive")
        BigDecimal distance,
        BigDecimal lat,
        BigDecimal lon,
        @Positive(message = "The rating must be positive")
        @Digits(integer = 3, fraction = 1, message = "The rating must be a valid monetary amount with up to 3 integer digits and 1 decimal digits")
        BigDecimal rating,
        List<Integer> hallIds) {
}
