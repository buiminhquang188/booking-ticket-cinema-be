package org.cybersoft.bookingticketcinemabe.payload.request.hall;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import org.cybersoft.bookingticketcinemabe.annotation.validator.ValidSeats;

import java.util.List;

public record HallUpdateRequest(
        @NotBlank
        String name,
        @Positive(message = "The totalSeats must be positive")
        Integer totalSeats,

        @Positive(message = "The branchId must be positive")
        Integer branchId,
        @ValidSeats
        List<HallUpdateSeat> seats
) {
}
