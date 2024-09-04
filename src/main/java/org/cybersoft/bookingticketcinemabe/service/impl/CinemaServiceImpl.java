package org.cybersoft.bookingticketcinemabe.service.impl;

import lombok.RequiredArgsConstructor;
import org.cybersoft.bookingticketcinemabe.dto.CinemaDetailDTO;
import org.cybersoft.bookingticketcinemabe.entity.CinemaEntity;
import org.cybersoft.bookingticketcinemabe.exception.runtime.CinemaNotFoundException;
import org.cybersoft.bookingticketcinemabe.mapper.CinemaMapper;
import org.cybersoft.bookingticketcinemabe.repository.CinemaRepository;
import org.cybersoft.bookingticketcinemabe.service.CinemaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CinemaServiceImpl implements CinemaService {
    private final CinemaRepository cinemaRepository;
    private final CinemaMapper cinemaMapper;

    @Override
    public Page<?> getCinemas(int pageNo, int pageSize, String name) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);

        return this.cinemaRepository.findAll(pageable)
                .map(cinemaMapper::toCinemaDTO);
    }

    @Override
    public CinemaDetailDTO getCinema(int id) {
        Optional<CinemaEntity> cinema = this.cinemaRepository.findById(id);
        return cinema.map(cinemaMapper::toCinemaDetailDto)
                .orElseThrow(() -> new CinemaNotFoundException(id));
    }
}
