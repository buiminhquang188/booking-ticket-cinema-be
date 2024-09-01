package org.cybersoft.bookingticketcinemabe.service.impl;

import org.cybersoft.bookingticketcinemabe.mapper.CinemaMapper;
import org.cybersoft.bookingticketcinemabe.repository.CinemaRepository;
import org.cybersoft.bookingticketcinemabe.service.CinemaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CinemaServiceImpl implements CinemaService {
    private final CinemaRepository cinemaRepository;
    private final CinemaMapper cinemaMapper;

    public CinemaServiceImpl(CinemaRepository cinemaRepository, CinemaMapper cinemaMapper) {
        this.cinemaRepository = cinemaRepository;
        this.cinemaMapper = cinemaMapper;
    }

    @Override
    public Page<?> getCinemas(int pageNo, int pageSize, String name) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);

        return this.cinemaRepository.findAll(pageable)
                .map(cinemaMapper::toCinemaDTO);
    }
}
