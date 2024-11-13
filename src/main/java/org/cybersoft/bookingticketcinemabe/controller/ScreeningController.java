package org.cybersoft.bookingticketcinemabe.controller;

import jakarta.validation.Valid;
import org.cybersoft.bookingticketcinemabe.payload.request.screening.ScreeningCreationRequest;
import org.cybersoft.bookingticketcinemabe.payload.request.screening.ScreeningCriteria;
import org.cybersoft.bookingticketcinemabe.payload.request.screening.ScreeningUpdateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping
public interface ScreeningController {
    @GetMapping("/screenings")
    ResponseEntity<?> getScreenings(ScreeningCriteria screeningCriteria);

    @GetMapping("/screening/{id}")
    ResponseEntity<?> getScreening(@PathVariable Integer id);

    @PostMapping("/screening")
    ResponseEntity<?> createScreening(@RequestBody @Valid ScreeningCreationRequest request);

    @PutMapping("/screening/{id}")
    ResponseEntity<?> updateScreening(@PathVariable Integer id, @RequestBody @Valid ScreeningUpdateRequest request);

    @DeleteMapping("/screening/{id}")
    ResponseEntity<?> deleteScreening(@PathVariable Integer id);
}
