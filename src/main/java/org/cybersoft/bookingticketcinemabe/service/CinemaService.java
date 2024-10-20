package org.cybersoft.bookingticketcinemabe.service;

import org.cybersoft.bookingticketcinemabe.dto.PageableDTO;
import org.cybersoft.bookingticketcinemabe.dto.cinema.CinemaDetailDTO;
import org.cybersoft.bookingticketcinemabe.payload.request.CinemaCreationRequest;
import org.cybersoft.bookingticketcinemabe.payload.request.CinemaCriteria;
import org.cybersoft.bookingticketcinemabe.payload.request.CinemaUpdateRequest;

public interface CinemaService {
    PageableDTO<?> getCinemas(CinemaCriteria cinemaCriteria);

    CinemaDetailDTO getCinema(int id);

    CinemaDetailDTO createCinema(CinemaCreationRequest cinemaCreationRequest);

    CinemaDetailDTO updateCinema(Integer id, CinemaUpdateRequest cinemaUpdateRequest);

    void deleteCinema(Integer id);
}
