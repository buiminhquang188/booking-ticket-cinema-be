package org.cybersoft.bookingticketcinemabe.query.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JooqPaginate {
    private Integer pageNumber;
    private Integer pageSize;
    private Integer totalPage;
    private Long totalElement;
}
