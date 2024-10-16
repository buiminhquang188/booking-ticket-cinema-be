package org.cybersoft.bookingticketcinemabe.payload.request.seat;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.cybersoft.bookingticketcinemabe.payload.request.pagination.Pagination;

@EqualsAndHashCode(callSuper = true)
@Data
public class SeatCriteria extends Pagination {
    private String seatRow;
    private String seatColumn;
    private String seatType;
    private String isActive;
    private String hallId;
}
