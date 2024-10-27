package org.cybersoft.bookingticketcinemabe.payload.request.movie;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.cybersoft.bookingticketcinemabe.payload.request.pagination.Pagination;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
public class MovieCriteria extends Pagination {
    private Integer id;

    private Byte rating;

    private Integer time;  // Time in minutes unit

    private LocalDateTime startDate;

    private LocalDateTime startDateFrom;

    private LocalDateTime startDateTo;

    private String movieName;


}
