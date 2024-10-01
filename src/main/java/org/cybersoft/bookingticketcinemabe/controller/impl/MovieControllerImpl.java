package org.cybersoft.bookingticketcinemabe.controller.impl;

import lombok.RequiredArgsConstructor;
import org.cybersoft.bookingticketcinemabe.controller.MovieController;
import org.cybersoft.bookingticketcinemabe.dto.MovieDTO;
import org.cybersoft.bookingticketcinemabe.dto.PageableDTO;
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
    public ResponseEntity<?> getMovie(int id) {
        MovieDTO movie = movieService.getMovie(id);
        return ResponseEntity.ok(
                BaseResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message(HttpStatus.OK.getReasonPhrase())
                        .data(movie)
                        .build()
        );
    }
}