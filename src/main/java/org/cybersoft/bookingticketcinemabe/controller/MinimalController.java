package org.cybersoft.bookingticketcinemabe.controller;

import org.cybersoft.bookingticketcinemabe.dto.minimal.MinimalDTO;
import org.cybersoft.bookingticketcinemabe.dto.PageableDTO;
import org.cybersoft.bookingticketcinemabe.payload.request.minimal.MinimalCriteria;
import org.cybersoft.bookingticketcinemabe.payload.response.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("/minimal")
public interface MinimalController {
    @GetMapping("/movies")
    ResponseEntity<?> getMovies(@RequestParam(defaultValue = "0") int pageNo,
                                @RequestParam(defaultValue = "10") int pageLimit,

                                @RequestParam(defaultValue = "movieName") String sortBy);

    @GetMapping("/branches")
    ResponseEntity<?> getBranches(MinimalCriteria minimalCriteria);

    @GetMapping("/screenings")
    ResponseEntity<?> getScreenings(@RequestParam(defaultValue = "0") int pageNo,
                                    @RequestParam(defaultValue = "10") int pageLimit,
                                    @RequestParam(defaultValue = "startTime") String sortBy);


    @GetMapping("/cinemas")
    ResponseEntity<BaseResponse<PageableDTO<List<MinimalDTO>>>> getCinemas(MinimalCriteria minimalCriteria);

    @GetMapping("/districts")
    ResponseEntity<BaseResponse<PageableDTO<List<MinimalDTO>>>> getDistricts(MinimalCriteria minimalCriteria);
}

