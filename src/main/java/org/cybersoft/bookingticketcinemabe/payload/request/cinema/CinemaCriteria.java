package org.cybersoft.bookingticketcinemabe.payload.request.cinema;

import org.cybersoft.bookingticketcinemabe.query.enums.Order;

import java.time.LocalDateTime;

public record CinemaCriteria(
        String name,
        Integer totalCinemaHall,
        LocalDateTime createdAtFrom,
        LocalDateTime createdAtTo,
        LocalDateTime updatedAtFrom,
        LocalDateTime updatedAtTo,
        Integer pageNo,
        Integer pageSize,
        String sort,
        Order order
) {
    public CinemaCriteria {
        if (pageNo == null || pageNo < 0) {
            pageNo = 1;
        }
        if (pageSize == null || pageSize < 1) {
            pageSize = 10;
        }
        if (sort == null || sort.isBlank()) {
            sort = "id";
        }
        if (order == null) {
            order = Order.ASC;
        }
    }
}
