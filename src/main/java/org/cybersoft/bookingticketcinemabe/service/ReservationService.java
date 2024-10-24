package org.cybersoft.bookingticketcinemabe.service;

import org.cybersoft.bookingticketcinemabe.dto.ReservationDTO;
import org.cybersoft.bookingticketcinemabe.payload.request.reservation.ReservationBookingRequest;

public interface ReservationService {
    ReservationDTO bookingTicket(Integer screeningId, ReservationBookingRequest reservationBookingRequest);

    ReservationDTO cancelBooking(Integer screeningId, Integer reservationId);
}
