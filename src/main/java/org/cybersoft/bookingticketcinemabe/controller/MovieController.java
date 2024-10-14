package org.cybersoft.bookingticketcinemabe.controller;

import org.cybersoft.bookingticketcinemabe.payload.request.MovieCreationRequest;
import org.cybersoft.bookingticketcinemabe.payload.request.MovieUpdateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping
public interface MovieController {
    @GetMapping("/movies")
    ResponseEntity<?> getMovies(@RequestParam(defaultValue = "0") int pageNo,
                                @RequestParam(defaultValue = "10") int pageLimit,
                                @RequestParam(defaultValue = "id") String sortBy);

    @GetMapping("/movie/{id}")
    ResponseEntity<?> getMovie(@PathVariable Integer id);

    @PostMapping("/movie")
    ResponseEntity<?> createMovie(@RequestBody MovieCreationRequest request);

    @PutMapping("/movie/{id}")
    ResponseEntity<?> updateMovie(@PathVariable Integer id, @RequestBody MovieUpdateRequest request);

    @DeleteMapping("/movie/{id}")
    ResponseEntity<?> deleteMovie(@PathVariable Integer id);
}
