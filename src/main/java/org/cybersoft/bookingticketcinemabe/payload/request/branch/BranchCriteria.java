package org.cybersoft.bookingticketcinemabe.payload.request.branch;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.cybersoft.bookingticketcinemabe.payload.request.pagination.Pagination;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
public class BranchCriteria extends Pagination {
    private Integer id;

    private String name;

    private BigDecimal distance;

    private BigDecimal rating;

    private String address;

}
