package org.cybersoft.bookingticketcinemabe.dto.statistic;

import lombok.Data;
import org.cybersoft.bookingticketcinemabe.enums.ScreeningStatus;

@Data
public class StatisticScreeningDTO {
    private Integer id;
    private Integer total;
    private Integer totalSeatsEmpty;
    private Integer totalSeatsBooked;
    private ScreeningStatus status;
    private String movieName;
    private String hallName;
    private String branchName;
    private String branchAddress;
}
