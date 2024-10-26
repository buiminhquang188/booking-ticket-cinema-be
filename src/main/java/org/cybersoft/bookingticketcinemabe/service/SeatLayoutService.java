package org.cybersoft.bookingticketcinemabe.service;

import org.cybersoft.bookingticketcinemabe.dto.hall.HallDetailSeatLayoutDTO;

import java.util.List;

public interface SeatLayoutService {
    List<List<HallDetailSeatLayoutDTO>> getSeatLayoutByHallId(Integer hallId);
}
