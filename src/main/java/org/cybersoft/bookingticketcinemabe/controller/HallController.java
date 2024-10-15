package org.cybersoft.bookingticketcinemabe.controller;

import jakarta.validation.Valid;
import org.cybersoft.bookingticketcinemabe.payload.request.hall.HallCreationRequest;
import org.cybersoft.bookingticketcinemabe.payload.request.hall.HallCriteria;
import org.cybersoft.bookingticketcinemabe.payload.request.hall.HallUpdateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping
public interface HallController {
    @GetMapping("/halls")
    ResponseEntity<?> getHalls(HallCriteria hallCriteria);

    @GetMapping("/hall/{id}")
    ResponseEntity<?> getHallById(@PathVariable Integer id);

    @PostMapping("/hall")
    ResponseEntity<?> createHall(@RequestBody HallCreationRequest request);

    @PatchMapping("/hall/{id}")
    ResponseEntity<?> updateHall(@PathVariable Integer id, @RequestBody @Valid HallUpdateRequest request);

    @DeleteMapping("/hall/{id}")
    ResponseEntity<?> deleteHall(@PathVariable Integer id);

    @GetMapping("/hall/{id}/seats-layout")
    ResponseEntity<?> getSeatsLayout(@PathVariable Integer id);
}
