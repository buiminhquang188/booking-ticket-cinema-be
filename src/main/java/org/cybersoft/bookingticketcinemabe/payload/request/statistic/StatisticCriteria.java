package org.cybersoft.bookingticketcinemabe.payload.request.statistic;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StatisticCriteria {
    private LocalDateTime createdAtFrom;
    private LocalDateTime createdAtTo;
    private LocalDateTime updatedAtFrom;
    private LocalDateTime updatedAtTo;
}
