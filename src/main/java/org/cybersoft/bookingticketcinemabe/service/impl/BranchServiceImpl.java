package org.cybersoft.bookingticketcinemabe.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.cybersoft.bookingticketcinemabe.dto.PageableDTO;
import org.cybersoft.bookingticketcinemabe.dto.branch.BranchDTO;
import org.cybersoft.bookingticketcinemabe.entity.BranchEntity;
import org.cybersoft.bookingticketcinemabe.entity.BranchEntity_;
import org.cybersoft.bookingticketcinemabe.entity.HallEntity;
import org.cybersoft.bookingticketcinemabe.entity.MovieEntity;
import org.cybersoft.bookingticketcinemabe.exception.NotFoundException;
import org.cybersoft.bookingticketcinemabe.mapper.pagination.PageableMapper;
import org.cybersoft.bookingticketcinemabe.mapper.branch.BranchDetailMapper;
import org.cybersoft.bookingticketcinemabe.mapper.branch.BranchMapper;
import org.cybersoft.bookingticketcinemabe.payload.request.branch.BranchCreationRequest;
import org.cybersoft.bookingticketcinemabe.payload.request.branch.BranchCriteria;
import org.cybersoft.bookingticketcinemabe.payload.request.branch.BranchUpdateRequest;
import org.cybersoft.bookingticketcinemabe.query.CriteriaApiHelper;
import org.cybersoft.bookingticketcinemabe.query.dto.Pageable;
import org.cybersoft.bookingticketcinemabe.query.impl.SelectQueryImpl;
import org.cybersoft.bookingticketcinemabe.repository.*;
import org.cybersoft.bookingticketcinemabe.service.BranchService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BranchServiceImpl implements BranchService {
    private final BranchRepository branchRepository;
    private final CinemaRepository cinemaRepository;
    private final DistrictRepository districtRepository;
    private final MovieRepository movieRepository;
    private final HallRepository hallRepository;
    private final BranchMapper branchMapper;
    private final BranchDetailMapper branchDetailMapper;

    private final CriteriaApiHelper criteriaApiHelper;

    @Override
    public PageableDTO<?> getBranches(BranchCriteria branchCriteria) {
        Pageable pageable = Pageable.builder()
                .pageNumber(branchCriteria.getPageNo())
                .pageSize(branchCriteria.getPageLimit())
                .sortDefinition(Pageable.sort(branchCriteria.getSort(), branchCriteria.getOrder()))
                .build();
        SelectQueryImpl<BranchEntity> branch = this.criteriaApiHelper.select(BranchEntity.class);

        if (branchCriteria.getId() != null) {
            branch.equal(BranchEntity_.id, branchCriteria.getId());
        }
        if (branchCriteria.getName() != null) {
            branch.like(BranchEntity_.name, branchCriteria.getName());
        }
        if (branchCriteria.getDistance() != null) {
            branch.equal(BranchEntity_.distance, branchCriteria.getDistance());
        }
        if (branchCriteria.getRating() != null) {
            branch.equal(BranchEntity_.rating, branchCriteria.getRating());
        }
        if (branchCriteria.getAddress() != null) {
            branch.like(BranchEntity_.address, branchCriteria.getAddress());
        }
        if (branchCriteria.getCreatedAtFrom() != null && branchCriteria.getCreatedAtTo() != null) {
            branch.between(BranchEntity_.createdAt.getName(), branchCriteria.getCreatedAtFrom(), branchCriteria.getCreatedAtTo());
        }

        if (branchCriteria.getUpdatedAtFrom() != null && branchCriteria.getUpdatedAtTo() != null) {
            branch.between(BranchEntity_.updatedAt.getName(), branchCriteria.getUpdatedAtFrom(), branchCriteria.getUpdatedAtTo());
        }

        return new PageableMapper<>().toDTO(branch.findAll(pageable).map(branchDetailMapper::toDTO));
    }

    @Override
    public BranchDTO getBranch(Integer id) {
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
        if (request.movieIds() != null && !request.movieIds().isEmpty() && !request.movieIds().contains(null)) {
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

    @Transactional
    @Override
    public BranchDTO updateBranch(Integer id, BranchUpdateRequest request) {
        BranchEntity branch = branchRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found branch"));

        if (branch != null) branchMapper.update(branch, request);

        if (request.cinemaId() != null)
            branch.setCinema(cinemaRepository.findById(request.cinemaId())
                    .orElseThrow(() -> new NotFoundException("Not found cinema")));

        if (request.districtId() != null)
            branch.setDistrict(districtRepository.findById(request.districtId())
                    .orElseThrow(() -> new NotFoundException("Not found district")));

        if (request.movieIds() != null && !request.movieIds().isEmpty() && branch.getMovies() != null && !request.movieIds().contains(null)) {
            //Remove movie not exist in request
            List<Integer> moviesRemove = new ArrayList<>(branch.getMovies().stream().map(MovieEntity::getId)
                    .filter(movieId -> !request.movieIds().contains(movieId))
                    .toList());
            if (!moviesRemove.isEmpty()) {
                moviesRemove.forEach((movieId) -> {
                    branch.getMovies().stream()
                            .filter(movieEntity -> Objects.equals(movieEntity.getId(), movieId))
                            .findFirst()
                            .ifPresent(branch::removeMovie);
                });
                //Add new movie exist in request
                List<Integer> moviesAdd = request.movieIds().stream()
                        .filter((movieId) -> !branch.getMovies()
                                .stream().map(MovieEntity::getId)
                                .collect(Collectors.toSet())
                                .contains(movieId)).toList();
                if (!moviesAdd.isEmpty()) {
                    moviesAdd.forEach((movieId) -> branch.addMovie(movieRepository.findById(movieId)
                            .orElseThrow(() -> new NotFoundException("Not found movie with id: " + movieId))));
                }

            }
        }

        if (request.hallIds() != null && !request.hallIds().isEmpty() && branch.getHalls() != null && !request.hallIds().contains(null)) {

            int hallSize = Objects.requireNonNull(branch).getHalls().size();
            for (int i = 0; i < hallSize; i++) branch.removeHall(branch.getHalls().get(0));

            request.hallIds().forEach(hallId -> {
                HallEntity hallEntity = hallRepository.findById(hallId)
                        .orElseThrow(() -> new NotFoundException("Not found hall with id: " + hallId));
                if (hallEntity != null) branch.addHall(hallEntity);
            });
        }

        branchRepository.save(branch);
        return branchMapper.toDTO(branch);
    }

    @Transactional
    @Override
    public void deleteBranch(Integer id) {
        BranchEntity branchDelete = branchRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found branch"));

        if (branchDelete != null) {
            branchDelete.getCinema().getBranches().remove(branchDelete);
            branchDelete.getDistrict().getBranches().remove(branchDelete);

            int hallSize = Objects.requireNonNull(branchDelete).getHalls().size();
            for (int i = 0; i < hallSize; i++) branchDelete.removeHall(branchDelete.getHalls().get(0));

            for (MovieEntity movie : branchDelete.getMovies()) {
                movie.getBranches().remove(branchDelete);
            }
            branchDelete.setMovies(null);
            branchRepository.delete(branchDelete);
        }
    }
}
