package org.cybersoft.bookingticketcinemabe.payload.request.cinema;

import java.util.List;

public record CinemaCreationRequest(
        String name,
        String image,
        List<Integer> provinces,
        List<Integer> branches
) {
}
