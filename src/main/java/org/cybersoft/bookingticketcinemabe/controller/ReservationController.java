package org.cybersoft.bookingticketcinemabe.controller;

import org.cybersoft.bookingticketcinemabe.payload.request.reservation.ReservationBookingRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping
public interface ReservationController {
    @PostMapping("/booking/{screeningId}")
    ResponseEntity<?> bookingTicket(@PathVariable("screeningId") Integer screeningId,
                                    @RequestBody ReservationBookingRequest reservationBookingRequest);
}
