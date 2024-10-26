package org.cybersoft.bookingticketcinemabe.service.impl;

import lombok.RequiredArgsConstructor;
import org.cybersoft.bookingticketcinemabe.dto.PageableDTO;
import org.cybersoft.bookingticketcinemabe.mapper.MinimalMapper;
import org.cybersoft.bookingticketcinemabe.mapper.PageableMapper;
import org.cybersoft.bookingticketcinemabe.repository.BranchRepository;
import org.cybersoft.bookingticketcinemabe.repository.MovieRepository;
import org.cybersoft.bookingticketcinemabe.repository.ScreeningRepository;
import org.cybersoft.bookingticketcinemabe.service.MinimalService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MinimalServiceImpl implements MinimalService {
    private final ScreeningRepository screeningRepository;
    private final BranchRepository branchRepository;
    private final MovieRepository movieRepository;
    private final MinimalMapper minimalMapper;

    @Override
    public PageableDTO<?> getScreenings(int pageNo, int pageLimit, String sortBy) {
        Pageable pageable = PageRequest.of(pageNo, pageLimit, Sort.by(sortBy));
        Page<?> page = this.screeningRepository.findAll(pageable).map(minimalMapper::toScreeningMinimalDTO);
        return new PageableMapper<>().toDTO(page);
    }

    @Override
    public PageableDTO<?> getMovies(int pageNo, int pageLimit, String sortBy) {
        Pageable pageable = PageRequest.of(pageNo, pageLimit, Sort.by(sortBy));
        Page<?> page = this.movieRepository.findAll(pageable).map(minimalMapper::toMovieDetailDTO);
        return new PageableMapper<>().toDTO(page);
    }

    @Override
    public PageableDTO<?> getBranches(int pageNo, int pageLimit, String sortBy) {
        Pageable pageable = PageRequest.of(pageNo, pageLimit, Sort.by(sortBy));
        Page<?> page = this.branchRepository.findAll(pageable).map(minimalMapper::toBranchMinimalDTO);
        return new PageableMapper<>().toDTO(page);
    }
}
