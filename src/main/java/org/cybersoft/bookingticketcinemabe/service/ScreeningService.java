package org.cybersoft.bookingticketcinemabe.service;

import org.cybersoft.bookingticketcinemabe.dto.PageableDTO;
import org.cybersoft.bookingticketcinemabe.dto.screening.ScreeningDTO;
import org.cybersoft.bookingticketcinemabe.dto.screening.ScreeningMinimalDTO;
import org.cybersoft.bookingticketcinemabe.payload.request.ScreeningCreationRequest;
import org.cybersoft.bookingticketcinemabe.payload.request.ScreeningUpdateRequest;

public interface ScreeningService {
    PageableDTO<?> getScreenings(int pageNo, int pageLimit, String sortBy);

    ScreeningDTO getScreening(Integer id);


    ScreeningMinimalDTO createScreening(ScreeningCreationRequest request);

    ScreeningDTO updateScreening(Integer id, ScreeningUpdateRequest request);

    void deleteScreening(Integer id);
}
