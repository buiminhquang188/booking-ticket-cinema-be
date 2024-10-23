package org.cybersoft.bookingticketcinemabe.service;

import org.cybersoft.bookingticketcinemabe.payload.request.reservation.ReservationBookingRequest;

public interface ReservationService {
    Object bookingTicket(Integer screeningId, ReservationBookingRequest reservationBookingRequest);
}
