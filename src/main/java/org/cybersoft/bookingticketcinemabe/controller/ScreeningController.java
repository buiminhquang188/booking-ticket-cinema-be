package org.cybersoft.bookingticketcinemabe.controller;

import org.cybersoft.bookingticketcinemabe.payload.request.ScreeningCreationRequest;
import org.cybersoft.bookingticketcinemabe.payload.request.ScreeningUpdateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping
public interface ScreeningController {
    @GetMapping("/screenings")
    public ResponseEntity<?> getScreenings(@RequestParam(defaultValue = "0") int pageNo,
                                           @RequestParam(defaultValue = "10") int pageLimit,
                                           @RequestParam(defaultValue = "id") String sortBy);

    @GetMapping("/screening/{id}")
    public ResponseEntity<?> getScreening(@PathVariable Integer id);

    @PostMapping("/screening")
    ResponseEntity<?> createScreening(@RequestBody ScreeningCreationRequest request);

    @PutMapping("/screening/{id}")
    ResponseEntity<?> updateScreening(@PathVariable Integer id, @RequestBody ScreeningUpdateRequest request);

    @DeleteMapping("/screening/{id}")
    ResponseEntity<?> deleteScreening(@PathVariable Integer id);
}
