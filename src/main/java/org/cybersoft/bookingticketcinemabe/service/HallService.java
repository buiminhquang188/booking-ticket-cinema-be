package org.cybersoft.bookingticketcinemabe.service;

import org.cybersoft.bookingticketcinemabe.dto.HallDetailDTO;
import org.cybersoft.bookingticketcinemabe.dto.PageableDTO;
import org.cybersoft.bookingticketcinemabe.payload.request.HallCreationRequest;
import org.cybersoft.bookingticketcinemabe.payload.request.HallCriteria;
import org.cybersoft.bookingticketcinemabe.payload.request.HallUpdateRequest;

public interface HallService {
    PageableDTO<?> getHalls(HallCriteria hallCriteria);

    HallDetailDTO getHall(Integer id);

    HallDetailDTO createHall(HallCreationRequest request);

    HallDetailDTO updateHall(Integer id, HallUpdateRequest request);

    void deleteHall(Integer id);
}
