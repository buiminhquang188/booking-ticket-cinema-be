package org.cybersoft.bookingticketcinemabe.controller;

import jakarta.validation.Valid;
import org.cybersoft.bookingticketcinemabe.payload.request.movie.MovieCreationRequest;
import org.cybersoft.bookingticketcinemabe.payload.request.movie.MovieCriteria;
import org.cybersoft.bookingticketcinemabe.payload.request.movie.MovieUpdateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping
public interface MovieController {
    @GetMapping("/movies")
    ResponseEntity<?> getMovies(MovieCriteria movieCriteria);

    @GetMapping("/movie/{id}")
    ResponseEntity<?> getMovie(@PathVariable Integer id);

    @PostMapping("/movie")
    ResponseEntity<?> createMovie(@RequestBody @Valid MovieCreationRequest request);

    @PutMapping("/movie/{id}")
    ResponseEntity<?> updateMovie(@PathVariable Integer id, @RequestBody @Valid MovieUpdateRequest request);

    @DeleteMapping("/movie/{id}")
    ResponseEntity<?> deleteMovie(@PathVariable Integer id);
}
