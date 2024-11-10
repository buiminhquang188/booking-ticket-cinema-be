package org.cybersoft.bookingticketcinemabe.controller;

import org.cybersoft.bookingticketcinemabe.payload.request.statistic.StatisticCriteria;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping
public interface StatisticController {
    @GetMapping("/statistic")
    ResponseEntity<?> getStatistic(StatisticCriteria statisticCriteria);
}
