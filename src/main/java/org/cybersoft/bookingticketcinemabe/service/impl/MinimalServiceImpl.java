package org.cybersoft.bookingticketcinemabe.service.impl;

import lombok.RequiredArgsConstructor;
import org.cybersoft.bookingticketcinemabe.dto.MinimalDTO;
import org.cybersoft.bookingticketcinemabe.dto.PageableDTO;
import org.cybersoft.bookingticketcinemabe.entity.CinemaEntity;
import org.cybersoft.bookingticketcinemabe.entity.CinemaEntity_;
import org.cybersoft.bookingticketcinemabe.entity.DistrictEntity;
import org.cybersoft.bookingticketcinemabe.entity.DistrictEntity_;
import org.cybersoft.bookingticketcinemabe.mapper.MinimalMapper;
import org.cybersoft.bookingticketcinemabe.mapper.pagination.PageableMapper;
import org.cybersoft.bookingticketcinemabe.payload.request.minimal.MinimalCriteria;
import org.cybersoft.bookingticketcinemabe.query.CriteriaApiHelper;
import org.cybersoft.bookingticketcinemabe.query.impl.SelectQueryImpl;
import org.cybersoft.bookingticketcinemabe.repository.BranchRepository;
import org.cybersoft.bookingticketcinemabe.repository.MovieRepository;
import org.cybersoft.bookingticketcinemabe.repository.ScreeningRepository;
import org.cybersoft.bookingticketcinemabe.service.MinimalService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MinimalServiceImpl implements MinimalService {
    private final ScreeningRepository screeningRepository;

    private final BranchRepository branchRepository;

    private final MovieRepository movieRepository;

    private final MinimalMapper minimalMapper;

    private final CriteriaApiHelper criteriaApiHelper;

    @Override
    public PageableDTO<?> getScreenings(int pageNo, int pageLimit, String sortBy) {
        Pageable pageable = PageRequest.of(pageNo, pageLimit, Sort.by(sortBy));
        Page<?> page = this.screeningRepository.findAll(pageable)
                .map(minimalMapper::toScreeningMinimalDTO);
        return new PageableMapper<>().toDTO(page);
    }

    @Override
    public PageableDTO<?> getMovies(int pageNo, int pageLimit, String sortBy) {
        Pageable pageable = PageRequest.of(pageNo, pageLimit, Sort.by(sortBy));
        Page<?> page = this.movieRepository.findAll(pageable)
                .map(minimalMapper::toMovieDetailDTO);
        return new PageableMapper<>().toDTO(page);
    }

    @Override
    public PageableDTO<?> getBranches(int pageNo, int pageLimit, String sortBy) {
        Pageable pageable = PageRequest.of(pageNo, pageLimit, Sort.by(sortBy));
        Page<?> page = this.branchRepository.findAll(pageable)
                .map(minimalMapper::toBranchMinimalDTO);
        return new PageableMapper<>().toDTO(page);
    }

    @Override
    public PageableDTO<List<MinimalDTO>> getCinemas(MinimalCriteria minimalCriteria) {
        org.cybersoft.bookingticketcinemabe.query.dto.Pageable pageable = org.cybersoft.bookingticketcinemabe.query.dto.Pageable.builder()
                .pageNumber(minimalCriteria.getPageNo())
                .pageSize(minimalCriteria.getPageLimit())
                .build();

        SelectQueryImpl<CinemaEntity> cinemas = this.criteriaApiHelper.select(CinemaEntity.class);

        if (minimalCriteria.getName() != null) {
            cinemas.like(CinemaEntity_.name, minimalCriteria.getName());
        }

        return new PageableMapper<>().toDTO(
                cinemas.findAll(pageable)
                        .map(minimalMapper::toCinemaMinimalDTO)
        );
    }

    @Override
    public PageableDTO<List<MinimalDTO>> getDistricts(MinimalCriteria minimalCriteria) {
        org.cybersoft.bookingticketcinemabe.query.dto.Pageable pageable = org.cybersoft.bookingticketcinemabe.query.dto.Pageable.builder()
                .pageNumber(minimalCriteria.getPageNo())
                .pageSize(minimalCriteria.getPageLimit())
                .build();

        SelectQueryImpl<DistrictEntity> districts = this.criteriaApiHelper.select(DistrictEntity.class);

        if (minimalCriteria.getName() != null) {
            districts.like(DistrictEntity_.name, minimalCriteria.getName());
        }

        return new PageableMapper<>().toDTO(
                districts.findAll(pageable)
                        .map(minimalMapper::toDistrictMinimalDTO)
        );
    }
}
