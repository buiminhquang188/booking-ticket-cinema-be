package org.cybersoft.bookingticketcinemabe.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.cybersoft.bookingticketcinemabe.dto.PageableDTO;
import org.cybersoft.bookingticketcinemabe.dto.movie.MovieDTO;
import org.cybersoft.bookingticketcinemabe.entity.BranchEntity;
import org.cybersoft.bookingticketcinemabe.entity.MovieEntity;
import org.cybersoft.bookingticketcinemabe.entity.ScreeningEntity;
import org.cybersoft.bookingticketcinemabe.exception.NotFoundException;
import org.cybersoft.bookingticketcinemabe.mapper.PageableMapper;
import org.cybersoft.bookingticketcinemabe.mapper.movie.MovieMapper;
import org.cybersoft.bookingticketcinemabe.payload.request.movie.MovieCreationRequest;
import org.cybersoft.bookingticketcinemabe.payload.request.movie.MovieUpdateRequest;
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
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
    public MovieDTO getMovie(Integer id) {
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

    @Transactional
    @Override
    public MovieDTO updateMovie(Integer id, MovieUpdateRequest request) {
        MovieEntity movie = movieRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found movie"));

        if (movie != null) movieMapper.update(movie, request);

        if (request.branchIds() != null && !request.branchIds().isEmpty() && !request.branchIds().contains(null)) {
            //Remove branch not exist in request
            List<Integer> branchRemove = new ArrayList<>(movie.getBranches().stream().map(BranchEntity::getId)
                    .filter(branchId -> !request.branchIds().contains(branchId))
                    .toList());
            if (!branchRemove.isEmpty()) {
                branchRemove.forEach((branchId) -> {
                    movie.getBranches().stream()
                            .filter(branchEntity -> Objects.equals(branchEntity.getId(), branchId))
                            .findFirst()
                            .ifPresent(movie::removeBranch);
                });
            }

            //Add new branch exist in request
            List<Integer> branchesAdd = request.branchIds().stream()
                    .filter((branchId) -> !movie.getBranches()
                            .stream().map(BranchEntity::getId)
                            .collect(Collectors.toSet())
                            .contains(branchId)).toList();
            if (!branchesAdd.isEmpty()) {
                branchesAdd.forEach((branchId) -> movie.addBranch(branchRepository.findById(branchId)
                        .orElseThrow(() -> new NotFoundException("Not found branch with id: " + branchId))));
            }
        }

        if (request.screeningIds() != null && !request.screeningIds().isEmpty() && movie.getScreenings() != null && !request.screeningIds().contains(null)) {

            int screeningSize = Objects.requireNonNull(movie).getScreenings().size();
            for (int i = 0; i < screeningSize; i++) movie.removeScreening(movie.getScreenings().get(0));

            request.screeningIds().forEach(screeningId -> {
                ScreeningEntity screeningEntity = screeningRepository.findById(screeningId)
                        .orElseThrow(() -> new NotFoundException("Not found screening with id: " + screeningId));
                if (screeningEntity != null) movie.addScreening(screeningEntity);
            });

        }

        movieRepository.save(movie);
        return movieMapper.toDTO(movie);
    }

    @Override
    public void deleteMovie(Integer id) {
        MovieEntity movieDelete = movieRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found movie"));

        if (movieDelete != null) {

            int screeningSize = Objects.requireNonNull(movieDelete).getScreenings().size();
            for (int i = 0; i < screeningSize; i++) movieDelete.removeScreening(movieDelete.getScreenings().get(0));

            for (BranchEntity branch : movieDelete.getBranches()) {
                branch.getMovies().remove(movieDelete);
            }
            movieDelete.setBranches(null);
            movieRepository.delete(movieDelete);
        }
    }
}
