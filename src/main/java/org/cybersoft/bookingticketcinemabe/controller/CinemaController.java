package org.cybersoft.bookingticketcinemabe.controller;

import org.cybersoft.bookingticketcinemabe.payload.request.CinemaCreationRequest;
import org.cybersoft.bookingticketcinemabe.payload.request.CinemaCriteria;
import org.cybersoft.bookingticketcinemabe.payload.request.CinemaUpdateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping
public interface CinemaController {
    @GetMapping("/cinemas")
    ResponseEntity<?> getCinemas(CinemaCriteria cinemaCriteria);

    @GetMapping("/cinema/{id}")
    ResponseEntity<?> getCinema(@PathVariable int id);

    @PostMapping("/cinema")
    ResponseEntity<?> createCinema(@RequestBody CinemaCreationRequest cinemaCreationRequest);

    @PatchMapping("/cinema/{id}")
    ResponseEntity<?> updateCinema(@PathVariable Integer id, @RequestBody CinemaUpdateRequest cinemaUpdateRequest);

    @DeleteMapping("/cinema/{id}")
    ResponseEntity<?> deleteCinema(@PathVariable Integer id);
}
