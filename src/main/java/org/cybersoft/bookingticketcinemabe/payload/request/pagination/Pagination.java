package org.cybersoft.bookingticketcinemabe.payload.request.pagination;

import lombok.Data;
import org.cybersoft.bookingticketcinemabe.query.enums.Order;

import java.time.LocalDateTime;

@Data
public class Pagination {
    private Integer pageNo = 1;
    private Integer pageLimit = 10;
    private String sort = "id";
    private Order order = Order.ASC;
    private LocalDateTime createdAtFrom;
    private LocalDateTime createdAtTo;
    private LocalDateTime updatedAtFrom;
    private LocalDateTime updatedAtTo;
}
