package org.cybersoft.bookingticketcinemabe.controller.impl;

import lombok.RequiredArgsConstructor;
import org.cybersoft.bookingticketcinemabe.controller.MovieController;
import org.cybersoft.bookingticketcinemabe.dto.PageableDTO;
import org.cybersoft.bookingticketcinemabe.dto.movie.MovieDTO;
import org.cybersoft.bookingticketcinemabe.payload.request.MovieCreationRequest;
import org.cybersoft.bookingticketcinemabe.payload.request.MovieUpdateRequest;
import org.cybersoft.bookingticketcinemabe.payload.response.BaseResponse;
import org.cybersoft.bookingticketcinemabe.service.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MovieControllerImpl implements MovieController {
    private final MovieService movieService;

    @Override
    public ResponseEntity<?> getMovies(int pageNo, int pageLimit, String sortBy) {
        PageableDTO<?> movies = movieService.getMovies(pageNo, pageLimit, sortBy);
        return ResponseEntity.ok(BaseResponse.builder()
                .statusCode(HttpStatus.OK.value())
                .message(HttpStatus.OK.getReasonPhrase())
                .data(movies)
                .build()
        );
    }

    @Override
    public ResponseEntity<?> getMovie(Integer id) {
        MovieDTO movie = movieService.getMovie(id);
        return ResponseEntity.ok(
                BaseResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message(HttpStatus.OK.getReasonPhrase())
                        .data(movie)
                        .build()
        );
    }

    @Override
    public ResponseEntity<?> createMovie(MovieCreationRequest request) {
        MovieDTO movie = movieService.createMovie(request);
        return ResponseEntity.ok(
                BaseResponse.builder()
                        .statusCode(HttpStatus.CREATED.value())
                        .message(HttpStatus.CREATED.getReasonPhrase())
                        .data(movie)
                        .build()
        );
    }

    @Override
    public ResponseEntity<?> updateMovie(Integer id, MovieUpdateRequest request) {
        MovieDTO movie = movieService.updateMovie(id, request);
        return ResponseEntity.ok(
                BaseResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message(HttpStatus.OK.getReasonPhrase())
                        .data(movie)
                        .build()
        );
    }

    @Override
    public ResponseEntity<?> deleteMovie(Integer id) {
        movieService.deleteMovie(id);
        return ResponseEntity.ok(
                BaseResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message(HttpStatus.OK.getReasonPhrase())
                        .data(null)
                        .build()
        );
    }
}
