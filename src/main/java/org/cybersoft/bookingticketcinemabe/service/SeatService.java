package org.cybersoft.bookingticketcinemabe.service;

import org.cybersoft.bookingticketcinemabe.dto.PageableDTO;
import org.cybersoft.bookingticketcinemabe.dto.seat.SeatDetailDTO;
import org.cybersoft.bookingticketcinemabe.payload.request.seat.SeatCriteria;
import org.cybersoft.bookingticketcinemabe.payload.request.seat.SeatUpdateRequest;

import java.util.List;

public interface SeatService {
    PageableDTO<List<SeatDetailDTO>> getSeats(SeatCriteria seatCriteria);

    SeatDetailDTO getSeat(Integer id);

    SeatDetailDTO updateSeat(Integer id, SeatUpdateRequest seatUpdateRequest);
}
