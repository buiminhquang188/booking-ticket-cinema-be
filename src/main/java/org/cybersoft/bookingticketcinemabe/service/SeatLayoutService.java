package org.cybersoft.bookingticketcinemabe.service;

import org.cybersoft.bookingticketcinemabe.dto.hall.HallDetailSeatLayoutDTO;
import org.cybersoft.bookingticketcinemabe.dto.screening.ScreeningDetailSeatLayoutDTO;

import java.util.List;

public interface SeatLayoutService {
    List<List<HallDetailSeatLayoutDTO>> getSeatLayoutByHallId(Integer hallId);

    List<List<ScreeningDetailSeatLayoutDTO>> getSeatLayoutByScreeningId(Integer screeningId);
}
