package org.cybersoft.bookingticketcinemabe.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.cybersoft.bookingticketcinemabe.dto.BranchDTO;
import org.cybersoft.bookingticketcinemabe.dto.PageableDTO;
import org.cybersoft.bookingticketcinemabe.entity.BranchEntity;
import org.cybersoft.bookingticketcinemabe.entity.HallEntity;
import org.cybersoft.bookingticketcinemabe.exception.NotFoundException;
import org.cybersoft.bookingticketcinemabe.mapper.BranchMapper;
import org.cybersoft.bookingticketcinemabe.mapper.PageableMapper;
import org.cybersoft.bookingticketcinemabe.payload.request.BranchCreationRequest;
import org.cybersoft.bookingticketcinemabe.repository.*;
import org.cybersoft.bookingticketcinemabe.service.BranchService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class BranchServiceImpl implements BranchService {
    private final BranchRepository branchRepository;
    private final CinemaRepository cinemaRepository;
    private final DistrictRepository districtRepository;
    private final MovieRepository movieRepository;
    private final HallRepository hallRepository;
    private final BranchMapper branchMapper;

    @Override
    public PageableDTO<?> getBranches(int pageNo, int pageLimit, String sortBy) {
        Pageable pageable = PageRequest.of(pageNo, pageLimit, Sort.by(sortBy));
        Page<?> page = this.branchRepository.findAll(pageable).map(branchMapper::toDTO);
        return new PageableMapper<>().toDTO(page);
    }

    @Override
    public BranchDTO getBranch(int id) {
        return this.branchRepository.findById(id).map(branchMapper::toDTO)
                .orElseThrow(() -> new NotFoundException("Not found branch"));
    }

    @Transactional
    @Override
    public BranchDTO createBranch(BranchCreationRequest request) {
        BranchEntity branch = branchMapper.toEntity(request);
        if (request.cinemaId() != null)
            branch.setCinema(cinemaRepository.findById(request.cinemaId())
                    .orElseThrow(() -> new NotFoundException("Not found cinema")));
        if (request.districtId() != null)
            branch.setDistrict(districtRepository.findById(request.districtId())
                    .orElseThrow(() -> new NotFoundException("Not found district")));
        if (request.movieIds() != null && !request.movieIds().isEmpty()) {
            request.movieIds().forEach((id) -> branch.addMovie(movieRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("Not found movie with id: " + id))));

        }
        if (request.totalCineplexHall() != null && request.totalCineplexHall() > 0) {
            branch.setHalls(new ArrayList<>());
            for (int i = 0; i < request.totalCineplexHall(); i++) {
                branch.addHall(hallRepository.save(new HallEntity()));
            }
        }
        branchRepository.save(branch);
        return branchMapper.toDTO(branch);
    }
}
