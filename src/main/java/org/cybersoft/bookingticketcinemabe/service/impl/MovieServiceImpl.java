package org.cybersoft.bookingticketcinemabe.service.impl;

import lombok.RequiredArgsConstructor;
import org.cybersoft.bookingticketcinemabe.dto.MovieDTO;
import org.cybersoft.bookingticketcinemabe.dto.PageableDTO;
import org.cybersoft.bookingticketcinemabe.exception.NotFoundException;
import org.cybersoft.bookingticketcinemabe.mapper.MovieMapper;
import org.cybersoft.bookingticketcinemabe.mapper.PageableMapper;
import org.cybersoft.bookingticketcinemabe.repository.MovieRepository;
import org.cybersoft.bookingticketcinemabe.service.MovieService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;

    @Override
    public PageableDTO<?> getMovies(int pageNo, int pageLimit, String sortBy) {
        Pageable pageable = PageRequest.of(pageNo, pageLimit, Sort.by(sortBy));
        Page<?> page = this.movieRepository.findAll(pageable).map(movieMapper::toDTO);
        return new PageableMapper<>().toDTO(page);
    }

    @Override
    public MovieDTO getMovie(int id) {
        return this.movieRepository.findById(id).map(movieMapper::toDTO)
                .orElseThrow(() -> new NotFoundException("Not found movie"));
    }
}
