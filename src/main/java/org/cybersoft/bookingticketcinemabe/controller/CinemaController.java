package org.cybersoft.bookingticketcinemabe.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping
public interface CinemaController {
    @GetMapping("/cinemas")
    ResponseEntity<?> getCinemas(@RequestParam int pageNo, @RequestParam int pageSize, @RequestParam(required = false) String name);

    @GetMapping("/cinema/{id}")
    ResponseEntity<?> getCinema(@PathVariable int id);
}
