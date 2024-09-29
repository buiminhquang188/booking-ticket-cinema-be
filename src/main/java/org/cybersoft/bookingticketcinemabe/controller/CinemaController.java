package org.cybersoft.bookingticketcinemabe.controller;

import org.cybersoft.bookingticketcinemabe.payload.request.CinemaCreationRequest;
import org.cybersoft.bookingticketcinemabe.payload.request.CinemaUpdateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping
public interface CinemaController {
    @GetMapping("/cinemas")
    ResponseEntity<?> getCinemas(@RequestParam int pageNo, @RequestParam int pageSize, @RequestParam(required = false) String name);

    @GetMapping("/cinema/{id}")
    ResponseEntity<?> getCinema(@PathVariable int id);

    @PostMapping("/cinema")
    ResponseEntity<?> createCinema(@RequestBody CinemaCreationRequest cinemaCreationRequest);

    @PatchMapping("/cinema/{id}")
    ResponseEntity<?> updateCinema(@PathVariable Integer id, @RequestBody CinemaUpdateRequest cinemaUpdateRequest);
}
