package org.cybersoft.bookingticketcinemabe.payload.request.hall;

import jakarta.validation.constraints.NotBlank;
import org.cybersoft.bookingticketcinemabe.annotation.validator.ValidSeats;

import java.util.List;

public record HallUpdateRequest(
        @NotBlank
        String name,
        Integer totalSeats,
        Integer branchId,
        @ValidSeats
        List<HallUpdateSeat> seats
) {
}
