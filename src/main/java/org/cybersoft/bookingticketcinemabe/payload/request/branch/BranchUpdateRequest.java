package org.cybersoft.bookingticketcinemabe.payload.request.branch;

import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;

public record BranchUpdateRequest(

        String name,
        String logo,
        String avatar,
        String link,
        String address,

        Integer districtId,
        Integer cinemaId,
        List<Integer> movieIds,
        BigDecimal distance,
        BigDecimal lat,
        BigDecimal lon,
        List<@Positive(message = "ALL hallIds must be positive")
                Integer> hallIds) {
}
