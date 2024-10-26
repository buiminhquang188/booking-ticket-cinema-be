package org.cybersoft.bookingticketcinemabe.controller.impl;

import lombok.RequiredArgsConstructor;
import org.cybersoft.bookingticketcinemabe.controller.GetMinimalController;
import org.cybersoft.bookingticketcinemabe.dto.PageableDTO;
import org.cybersoft.bookingticketcinemabe.payload.response.BaseResponse;
import org.cybersoft.bookingticketcinemabe.service.MinimalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GetMinimalControllerImpl implements GetMinimalController {
    private final MinimalService minimalService;

    @Override
    public ResponseEntity<?> getMovies(int pageNo, int pageLimit, String sortBy) {
        PageableDTO<?> movies = minimalService.getMovies(pageNo, pageLimit, sortBy);
        return ResponseEntity.ok(BaseResponse.builder()
                .statusCode(HttpStatus.OK.value())
                .message(HttpStatus.OK.getReasonPhrase())
                .data(movies)
                .build()
        );
    }

    @Override
    public ResponseEntity<?> getBranches(int pageNo, int pageLimit, String sortBy) {
        PageableDTO<?> branches = minimalService.getBranches(pageNo, pageLimit, sortBy);
        return ResponseEntity.ok(BaseResponse.builder()
                .statusCode(HttpStatus.OK.value())
                .message(HttpStatus.OK.getReasonPhrase())
                .data(branches)
                .build()
        );
    }

    @Override
    public ResponseEntity<?> getScreenings(int pageNo, int pageLimit, String sortBy) {
        PageableDTO<?> screenings = minimalService.getScreenings(pageNo, pageLimit, sortBy);
        return ResponseEntity.ok(BaseResponse.builder()
                .statusCode(HttpStatus.OK.value())
                .message(HttpStatus.OK.getReasonPhrase())
                .data(screenings)
                .build()
        );
    }
}

