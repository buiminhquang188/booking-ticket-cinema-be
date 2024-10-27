package org.cybersoft.bookingticketcinemabe.payload.request.screening;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.cybersoft.bookingticketcinemabe.payload.request.pagination.Pagination;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
public class ScreeningCriteria extends Pagination {
    private Integer id;

    private LocalDateTime startTimeFrom;

    private LocalDateTime startTimeTo;

    private LocalDateTime endTimeFrom;

    private LocalDateTime endTimeTo;

    private String status;

    private Integer hallId;

    private Integer movieId;


}
