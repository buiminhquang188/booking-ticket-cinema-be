package org.cybersoft.bookingticketcinemabe.service;

import org.cybersoft.bookingticketcinemabe.dto.statistic.StatisticDTO;
import org.cybersoft.bookingticketcinemabe.payload.request.statistic.StatisticCriteria;

public interface StatisticService {
    StatisticDTO getStatistic(StatisticCriteria statisticCriteria);
}
