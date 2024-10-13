package org.cybersoft.bookingticketcinemabe.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.cybersoft.bookingticketcinemabe.dto.MovieDTO;
import org.cybersoft.bookingticketcinemabe.dto.PageableDTO;
import org.cybersoft.bookingticketcinemabe.entity.MovieEntity;
import org.cybersoft.bookingticketcinemabe.exception.NotFoundException;
import org.cybersoft.bookingticketcinemabe.mapper.MovieMapper;
import org.cybersoft.bookingticketcinemabe.mapper.PageableMapper;
import org.cybersoft.bookingticketcinemabe.payload.request.MovieCreationRequest;
import org.cybersoft.bookingticketcinemabe.repository.BranchRepository;
import org.cybersoft.bookingticketcinemabe.repository.MovieRepository;
import org.cybersoft.bookingticketcinemabe.repository.ScreeningRepository;
import org.cybersoft.bookingticketcinemabe.service.MovieService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final BranchRepository branchRepository;
    private final ScreeningRepository screeningRepository;
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

    @Transactional
    @Override
    public MovieDTO createMovie(MovieCreationRequest request) {
        MovieEntity movie = movieMapper.toEntity(request);
        if (request.branchIds() != null && !request.branchIds().isEmpty() && !request.branchIds().contains(null)) {
            movie.setBranches(new HashSet<>());
            request.branchIds().forEach((id) -> movie.addBranch(branchRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("Not found branch with id: " + id))));

        }
        if (request.screeningIds() != null && !request.screeningIds().isEmpty() && !request.screeningIds().contains(null)) {
            movie.setScreenings(new ArrayList<>());
            request.screeningIds().forEach((id) -> movie.addScreening(screeningRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("Not found screening with id: " + id))));

        }

        movieRepository.save(movie);
        return movieMapper.toDTO(movie);
    }
}
