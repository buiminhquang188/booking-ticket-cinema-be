package org.cybersoft.bookingticketcinemabe.service;

import org.cybersoft.bookingticketcinemabe.dto.HallDetailDTO;
import org.cybersoft.bookingticketcinemabe.dto.HallDetailSeatLayoutDTO;
import org.cybersoft.bookingticketcinemabe.dto.PageableDTO;
import org.cybersoft.bookingticketcinemabe.payload.request.hall.HallCreationRequest;
import org.cybersoft.bookingticketcinemabe.payload.request.hall.HallCriteria;
import org.cybersoft.bookingticketcinemabe.payload.request.hall.HallUpdateRequest;

import java.util.List;

public interface HallService {
    PageableDTO<?> getHalls(HallCriteria hallCriteria);

    HallDetailDTO getHall(Integer id);

    HallDetailDTO createHall(HallCreationRequest request);

    HallDetailDTO updateHall(Integer id, HallUpdateRequest request);

    void deleteHall(Integer id);

    List<List<HallDetailSeatLayoutDTO>> getSeatsLayout(Integer id);
}
