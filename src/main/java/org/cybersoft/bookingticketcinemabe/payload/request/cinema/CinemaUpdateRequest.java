package org.cybersoft.bookingticketcinemabe.payload.request.cinema;

import java.util.List;

public record CinemaUpdateRequest(
        String name,
        String image,
        List<Integer> provinces,
        List<Integer> branches) {
}
