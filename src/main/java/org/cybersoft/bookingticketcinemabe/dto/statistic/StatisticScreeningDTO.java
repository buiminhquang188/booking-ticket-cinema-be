package org.cybersoft.bookingticketcinemabe.dto.statistic;

import lombok.Data;

@Data
public class StatisticScreeningDTO {
    private Integer total;
    private Integer totalSeatsEmpty;
    private Integer totalSeatsBooked;
    private String movieName;
}
