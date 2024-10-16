package org.cybersoft.bookingticketcinemabe.controller;

import org.cybersoft.bookingticketcinemabe.payload.request.seat.SeatCriteria;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping
public interface SeatController {
    @GetMapping("/seats")
    ResponseEntity<?> getSeats(SeatCriteria seatCriteria);
}
