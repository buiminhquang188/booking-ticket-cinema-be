package org.cybersoft.bookingticketcinemabe.service;

import org.cybersoft.bookingticketcinemabe.payload.request.seat.SeatCriteria;

public interface SeatService {
    Object getSeats(SeatCriteria seatCriteria);
}
