package org.cybersoft.bookingticketcinemabe.controller;

import org.cybersoft.bookingticketcinemabe.dto.PageableDTO;
import org.cybersoft.bookingticketcinemabe.dto.minimal.MinimalDTO;
import org.cybersoft.bookingticketcinemabe.dto.minimal.MinimalHallDTO;
import org.cybersoft.bookingticketcinemabe.dto.minimal.MinimalMovieDTO;
import org.cybersoft.bookingticketcinemabe.payload.request.minimal.MinimalCriteria;
import org.cybersoft.bookingticketcinemabe.payload.response.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/minimal")
public interface MinimalController {
    @GetMapping("/movies")
    ResponseEntity<BaseResponse<PageableDTO<List<MinimalMovieDTO>>>> getMovies(MinimalCriteria minimalCriteria);

    @GetMapping("/branches")
    ResponseEntity<?> getBranches(MinimalCriteria minimalCriteria);

    @GetMapping("/screenings")
    ResponseEntity<?> getScreenings(MinimalCriteria minimalCriteria);

    @GetMapping("/cinemas")
    ResponseEntity<BaseResponse<PageableDTO<List<MinimalDTO>>>> getCinemas(MinimalCriteria minimalCriteria);

    @GetMapping("/districts")
    ResponseEntity<BaseResponse<PageableDTO<List<MinimalDTO>>>> getDistricts(MinimalCriteria minimalCriteria);

    @GetMapping("/provinces")
    ResponseEntity<BaseResponse<PageableDTO<List<MinimalDTO>>>> getProvinces(MinimalCriteria minimalCriteria);

    @GetMapping("/halls")
    ResponseEntity<BaseResponse<PageableDTO<List<MinimalHallDTO>>>> getHalls(MinimalCriteria minimalCriteria);
}

