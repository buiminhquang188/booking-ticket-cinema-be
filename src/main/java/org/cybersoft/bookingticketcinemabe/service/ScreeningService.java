package org.cybersoft.bookingticketcinemabe.service;

import org.cybersoft.bookingticketcinemabe.dto.PageableDTO;
import org.cybersoft.bookingticketcinemabe.dto.screening.ScreeningDTO;
import org.cybersoft.bookingticketcinemabe.dto.screening.ScreeningDetailDTO;
import org.cybersoft.bookingticketcinemabe.payload.request.screening.ScreeningCreationRequest;
import org.cybersoft.bookingticketcinemabe.payload.request.screening.ScreeningCriteria;
import org.cybersoft.bookingticketcinemabe.payload.request.screening.ScreeningUpdateRequest;

public interface ScreeningService {
    PageableDTO<?> getScreenings(ScreeningCriteria screeningCriteria);

    ScreeningDetailDTO getScreening(Integer id);

    ScreeningDTO createScreening(ScreeningCreationRequest request);

    ScreeningDTO updateScreening(Integer id, ScreeningUpdateRequest request);

    void deleteScreening(Integer id);
}
