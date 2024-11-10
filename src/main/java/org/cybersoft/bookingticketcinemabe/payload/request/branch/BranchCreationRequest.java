package org.cybersoft.bookingticketcinemabe.payload.request.branch;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;

public record BranchCreationRequest(
        String name,
        String logo,
        String avatar,
        String link,
        String address,
        Integer districtId,
        Integer cinemaId,
        List<Integer> movieIds,
        @Positive(message = "The distance must be positive")
        BigDecimal distance,

        @Min(value = -90, message = "Latitude must be greater than or equal to -90")
        @Max(value = 90, message = "Latitude must be less than or equal to 90")
        BigDecimal lat,
        @Min(value = -90, message = "Longitude must be greater than or equal to -90")
        @Max(value = 90, message = "Longitude must be less than or equal to 90")
        BigDecimal lon,
        BigDecimal rating,

        Integer totalCineplexHall) {
}
