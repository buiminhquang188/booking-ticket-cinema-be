package org.cybersoft.bookingticketcinemabe.payload.request;

import lombok.Data;

@Data
public class MinimalCriteria {
    private String name;
    private Integer pageNo = 1;
    private Integer pageLimit = 10;
}
