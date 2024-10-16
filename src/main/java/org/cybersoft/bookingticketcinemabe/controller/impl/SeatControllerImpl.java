package org.cybersoft.bookingticketcinemabe.controller.impl;

import lombok.AllArgsConstructor;
import org.cybersoft.bookingticketcinemabe.controller.SeatController;
import org.cybersoft.bookingticketcinemabe.payload.request.seat.SeatCriteria;
import org.cybersoft.bookingticketcinemabe.payload.response.BaseResponse;
import org.cybersoft.bookingticketcinemabe.service.SeatService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class SeatControllerImpl implements SeatController {
    private final SeatService seatService;

    @Override
    public ResponseEntity<?> getSeats(SeatCriteria seatCriteria) {
        Object seat = this.seatService.getSeats(seatCriteria);

        return ResponseEntity.ok(
                BaseResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message(HttpStatus.OK.getReasonPhrase())
                        .data(seat)
                        .build()
        );
    }
}
