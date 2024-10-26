package org.cybersoft.bookingticketcinemabe.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/minimal")
public interface GetMinimalController {
    @GetMapping("/movies")
    public ResponseEntity<?> getMovies(@RequestParam(defaultValue = "0") int pageNo,
                                       @RequestParam(defaultValue = "10") int pageLimit,

                                       @RequestParam(defaultValue = "movieName") String sortBy);

    @GetMapping("/branches")
    ResponseEntity<?> getBranches(@RequestParam(defaultValue = "0") int pageNo,
                                  @RequestParam(defaultValue = "10") int pageLimit,
                                  @RequestParam(defaultValue = "name") String sortBy);

    @GetMapping("/screenings")
    public ResponseEntity<?> getScreenings(@RequestParam(defaultValue = "0") int pageNo,
                                           @RequestParam(defaultValue = "10") int pageLimit,
                                           @RequestParam(defaultValue = "startTime") String sortBy);


}

