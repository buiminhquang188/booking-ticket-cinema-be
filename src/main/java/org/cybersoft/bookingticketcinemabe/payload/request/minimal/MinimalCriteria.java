package org.cybersoft.bookingticketcinemabe.payload.request.minimal;

import lombok.Data;

@Data
public class MinimalCriteria {
    private String search;
    private Integer pageNo = 1;
    private Integer pageLimit = 10;
}
