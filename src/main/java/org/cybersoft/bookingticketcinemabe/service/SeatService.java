package org.cybersoft.bookingticketcinemabe.service;

import org.cybersoft.bookingticketcinemabe.dto.PageableDTO;
import org.cybersoft.bookingticketcinemabe.dto.SeatDetailDTO;
import org.cybersoft.bookingticketcinemabe.payload.request.seat.SeatCriteria;

import java.util.List;

public interface SeatService {
    PageableDTO<List<SeatDetailDTO>> getSeats(SeatCriteria seatCriteria);

    SeatDetailDTO getSeat(Integer id);
}
