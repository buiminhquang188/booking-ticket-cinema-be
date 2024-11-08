package org.cybersoft.bookingticketcinemabe.payload.request.branch;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.List;

public record BranchCreationRequest(
        @NotEmpty(message = "The name must not empty")
        String name,
        String logo,
        String avatar,
        String link,
        String address,
        @Positive(message = "The cinemaId must be positive")
        Integer districtId,
        @Positive(message = "The cinemaId must be positive")
        Integer cinemaId,
        List<@Positive(message = "ALL movieIds must be positive")
                Integer> movieIds,
        @Positive(message = "The distance must be positive")
        BigDecimal distance,

        @Min(value = -90, message = "Latitude must be greater than or equal to -90")
        @Max(value = 90, message = "Latitude must be less than or equal to 90")
        BigDecimal lat,
        @Min(value = -90, message = "Longitude must be greater than or equal to -90")
        @Max(value = 90, message = "Longitude must be less than or equal to 90")
        BigDecimal lon,
        @Positive(message = "The rating must be positive")
        @Digits(integer = 3, fraction = 1, message = "The rating must be a valid monetary amount with up to 3 integer digits and 1 decimal digits")
        BigDecimal rating,

        @Positive(message = "The totalCineplexHall must be positive")
        Integer totalCineplexHall) {
}
