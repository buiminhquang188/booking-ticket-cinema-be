package org.cybersoft.bookingticketcinemabe.controller;

import org.cybersoft.bookingticketcinemabe.payload.request.MovieCreationRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping
public interface MovieController {
    @GetMapping("/movies")
    ResponseEntity<?> getMovies(@RequestParam(defaultValue = "0") int pageNo,
                                @RequestParam(defaultValue = "10") int pageLimit,
                                @RequestParam(defaultValue = "id") String sortBy);

    @GetMapping("/movie/{id}")
    ResponseEntity<?> getMovie(@PathVariable int id);

    @PostMapping("/movie")
    ResponseEntity<?> createMovie(@RequestBody MovieCreationRequest request);
}
