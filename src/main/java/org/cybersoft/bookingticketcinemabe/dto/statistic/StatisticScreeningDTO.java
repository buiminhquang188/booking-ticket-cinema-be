package org.cybersoft.bookingticketcinemabe.dto.statistic;

import lombok.Data;

@Data
public class StatisticScreeningDTO {
    private Integer id;
    private Integer total;
    private Integer totalSeatsEmpty;
    private Integer totalSeatsBooked;
    private String movieName;
    private String hallName;
    private String branchName;
    private String branchAddress;
}
