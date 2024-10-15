package org.cybersoft.bookingticketcinemabe.payload.request.hall;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.cybersoft.bookingticketcinemabe.annotation.validator.ValidSeats;

import java.util.List;

public record HallUpdateRequest(
        @NotBlank
        @NotNull
        String name,
        Integer totalSeats,
        Integer branchId,
        @ValidSeats
        List<HallUpdateSeat> seats
) {
}
