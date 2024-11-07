package org.cybersoft.bookingticketcinemabe.payload.request.hall;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

import java.util.List;

public record HallCreationRequest(
        @NotBlank
        @NotEmpty(message = "The name must not empty")
        String name,
        @Positive(message = "The totalSeats must be positive")
        @Digits(integer = 10, fraction = 0, message = "The totalSeats must be an integer")
        Integer totalSeats,
        @NotEmpty(message = "The branchId must not empty")
        @Positive(message = "The branchId must be positive")
        @Digits(integer = 10, fraction = 0, message = "The branchId must be an integer")
        Integer branchId,
        List<HallCreateSeat> seats
) {
}
