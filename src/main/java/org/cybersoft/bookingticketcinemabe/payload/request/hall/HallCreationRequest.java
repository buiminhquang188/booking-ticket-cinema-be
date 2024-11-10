package org.cybersoft.bookingticketcinemabe.payload.request.hall;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record HallCreationRequest(
        @NotBlank
        String name,
        Integer totalSeats,
        Integer branchId,
        List<@Valid HallCreateSeat> seats
) {
}
