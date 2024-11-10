package org.cybersoft.bookingticketcinemabe.dto.statistic;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class StatisticDTO {
    private StatisticUserDTO user;
    private StatisticCinemaDTO cinema;
    private List<StatisticBranchDTO> branches;
    private List<StatisticScreeningDTO> screenings;
}
