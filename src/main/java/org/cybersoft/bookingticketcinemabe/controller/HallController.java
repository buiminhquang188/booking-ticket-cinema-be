package org.cybersoft.bookingticketcinemabe.controller;

import org.cybersoft.bookingticketcinemabe.payload.request.HallCreationRequest;
import org.cybersoft.bookingticketcinemabe.payload.request.HallCriteria;
import org.cybersoft.bookingticketcinemabe.payload.request.HallUpdateRequest;
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
    ResponseEntity<?> updateHall(@PathVariable Integer id, @RequestBody HallUpdateRequest request);

    @DeleteMapping("/hall/{id}")
    ResponseEntity<?> deleteHall(@PathVariable Integer id);
}
