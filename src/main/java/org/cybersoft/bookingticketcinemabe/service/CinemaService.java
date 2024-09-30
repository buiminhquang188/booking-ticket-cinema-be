package org.cybersoft.bookingticketcinemabe.service;

import org.cybersoft.bookingticketcinemabe.dto.CinemaDetailDTO;
import org.cybersoft.bookingticketcinemabe.payload.request.CinemaCreationRequest;
import org.cybersoft.bookingticketcinemabe.payload.request.CinemaUpdateRequest;
import org.springframework.data.domain.Page;

public interface CinemaService {
    Page<?> getCinemas(int pageNo, int pageSize, String name);

    CinemaDetailDTO getCinema(int id);

    CinemaDetailDTO createCinema(CinemaCreationRequest cinemaCreationRequest);

    CinemaDetailDTO updateCinema(Integer id, CinemaUpdateRequest cinemaUpdateRequest);

    void deleteCinema(Integer id);
}
