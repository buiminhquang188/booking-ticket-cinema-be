package org.cybersoft.bookingticketcinemabe.controller.impl;

import lombok.AllArgsConstructor;
import org.cybersoft.bookingticketcinemabe.controller.ReservationController;
import org.cybersoft.bookingticketcinemabe.payload.request.reservation.ReservationBookingRequest;
import org.cybersoft.bookingticketcinemabe.payload.response.BaseResponse;
import org.cybersoft.bookingticketcinemabe.service.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ReservationControllerImpl implements ReservationController {

    private final ReservationService reservationService;

    @Override
    public ResponseEntity<?> bookingTicket(Integer screeningId, ReservationBookingRequest reservationBookingRequest) {
        Object reservation = this.reservationService.bookingTicket(screeningId, reservationBookingRequest);

        return ResponseEntity.ok(
                BaseResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message(HttpStatus.OK.getReasonPhrase())
                        .data(reservation)
                        .build()
        );
    }
}
