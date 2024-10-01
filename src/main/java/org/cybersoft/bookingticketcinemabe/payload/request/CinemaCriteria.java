package org.cybersoft.bookingticketcinemabe.payload.request;

import java.time.LocalDateTime;

public record CinemaCriteria(
        Integer pageNo,
        Integer pageSize,
        String name,
        Integer totalCinemaHall,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {

}
