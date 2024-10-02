package org.cybersoft.bookingticketcinemabe.payload.request;

import org.cybersoft.bookingticketcinemabe.query.utils.Order;

import java.time.LocalDateTime;

public record CinemaCriteria(
        Integer pageNo,
        Integer pageSize,
        String name,
        Integer totalCinemaHall,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        String sort,
        Order order
) {

}
