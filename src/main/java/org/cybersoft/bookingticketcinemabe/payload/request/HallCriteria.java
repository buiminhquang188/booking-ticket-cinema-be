package org.cybersoft.bookingticketcinemabe.payload.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.cybersoft.bookingticketcinemabe.payload.request.pagination.Pagination;

@EqualsAndHashCode(callSuper = true)
@Data
public class HallCriteria extends Pagination {
    private String name;
    private Integer totalSeats;
    private String branchId;
}
