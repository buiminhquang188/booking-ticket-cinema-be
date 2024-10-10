package org.cybersoft.bookingticketcinemabe.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.cybersoft.bookingticketcinemabe.dto.BranchDTO;
import org.cybersoft.bookingticketcinemabe.dto.PageableDTO;
import org.cybersoft.bookingticketcinemabe.entity.BranchEntity;
import org.cybersoft.bookingticketcinemabe.entity.HallEntity;
import org.cybersoft.bookingticketcinemabe.entity.MovieEntity;
import org.cybersoft.bookingticketcinemabe.exception.NotFoundException;
import org.cybersoft.bookingticketcinemabe.mapper.BranchMapper;
import org.cybersoft.bookingticketcinemabe.mapper.PageableMapper;
import org.cybersoft.bookingticketcinemabe.payload.request.BranchCreationRequest;
import org.cybersoft.bookingticketcinemabe.payload.request.BranchUpdateRequest;
import org.cybersoft.bookingticketcinemabe.repository.*;
import org.cybersoft.bookingticketcinemabe.service.BranchService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
                branch.getHalls().add(hallRepository.save(new HallEntity()));
            }
        }
        branchRepository.save(branch);
        return branchMapper.toDTO(branch);
    }

    @Transactional
    @Override
    public BranchDTO updateBranch(int id, BranchUpdateRequest request) {
        BranchEntity branch = branchRepository.findById(id).orElseThrow(() -> new NotFoundException("Not found branch"));
        if (branch != null) branchMapper.update(branch, request);

        if (request.cinemaId() != null)
            branch.setCinema(cinemaRepository.findById(request.cinemaId())
                    .orElseThrow(() -> new NotFoundException("Not found cinema")));
        if (request.districtId() != null)
            branch.setDistrict(districtRepository.findById(request.districtId())
                    .orElseThrow(() -> new NotFoundException("Not found district")));
        if (request.movieIds() != null && !request.movieIds().isEmpty() && branch.getMovies() != null) {
            List<Integer> moviesRemove = new ArrayList<>(branch.getMovies().stream().filter((movie) -> !request.movieIds().contains(movie.getId()))
                    .map(MovieEntity::getId)
                    .toList());

            for (MovieEntity movie : branch.getMovies()) {
                for (int i = 0; i < request.movieIds().size(); i++) {
                    if (Objects.equals(request.movieIds().get(i), movie.getId())) {
                        request.movieIds().remove(i);
                        break;
                    }
                }
                if (request.movieIds().isEmpty()) break;
            }
            for (MovieEntity movie : branch.getMovies()) {
                for (int i = 0; i < moviesRemove.size(); i++) {
                    if (Objects.equals(moviesRemove.get(i), movie.getId())) {
                        branch.removeMovie(movie);
                        moviesRemove.remove(i);
                        break;
                    }
                }
                if (moviesRemove.isEmpty()) break;
            }


            request.movieIds().forEach((movieId) -> branch.addMovie(movieRepository.findById(movieId)
                    .orElseThrow(() -> new NotFoundException("Not found movie with id: " + movieId))));

        }
        for (int i = 0; i < Objects.requireNonNull(branch).getHalls().size(); i++) {
            System.out.println(branch.getHalls().get(i).getId());
            branch.removeHall(branch.getHalls().get(i));
            i--;
        }
        request.hallIds().forEach(hallId -> {
            HallEntity hallEntity = hallRepository.findById(hallId)
                    .orElseThrow(() -> new NotFoundException("Not found hall with id: " + hallId));
            if (hallEntity != null) branch.addHall(hallEntity);
        });
        branchRepository.save(branch);
        return branchMapper.toDTO(branch);
    }
}
