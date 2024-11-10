package org.cybersoft.bookingticketcinemabe.controller.impl;

import lombok.AllArgsConstructor;
import org.cybersoft.bookingticketcinemabe.controller.StatisticController;
import org.cybersoft.bookingticketcinemabe.dto.statistic.StatisticDTO;
import org.cybersoft.bookingticketcinemabe.payload.request.statistic.StatisticCriteria;
import org.cybersoft.bookingticketcinemabe.payload.response.BaseResponse;
import org.cybersoft.bookingticketcinemabe.service.StatisticService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class StatisticControllerImpl implements StatisticController {
    private final StatisticService statisticService;

    @Override
    public ResponseEntity<?> getStatistic(StatisticCriteria statisticCriteria) {
        StatisticDTO statistic = this.statisticService.getStatistic(statisticCriteria);

        return ResponseEntity.ok(
                BaseResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message(HttpStatus.OK.getReasonPhrase())
                        .data(statistic)
                        .build()
        );
    }
}
