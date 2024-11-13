package org.cybersoft.bookingticketcinemabe.payload.request.cinema;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record CinemaUpdateRequest(
        @NotBlank
        String name,
        String image,
        @Valid
        List<Integer> provinces,
        @Valid
        List<Integer> branches) {
}
