package org.cybersoft.bookingticketcinemabe.controller.impl;

import lombok.RequiredArgsConstructor;
import org.cybersoft.bookingticketcinemabe.controller.MinimalController;
import org.cybersoft.bookingticketcinemabe.dto.PageableDTO;
import org.cybersoft.bookingticketcinemabe.dto.minimal.MinimalDTO;
import org.cybersoft.bookingticketcinemabe.payload.request.minimal.MinimalCriteria;
import org.cybersoft.bookingticketcinemabe.payload.response.BaseResponse;
import org.cybersoft.bookingticketcinemabe.service.MinimalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MinimalControllerImpl implements MinimalController {
    private final MinimalService minimalService;

    @Override
    public ResponseEntity<?> getMovies(MinimalCriteria minimalCriteria) {
        PageableDTO<?> movies = this.minimalService.getMovies(minimalCriteria);

        return ResponseEntity.ok(BaseResponse.builder()
                .statusCode(HttpStatus.OK.value())
                .message(HttpStatus.OK.getReasonPhrase())
                .data(movies)
                .build()
        );
    }

    @Override
    public ResponseEntity<?> getBranches(MinimalCriteria minimalCriteria) {
        PageableDTO<?> branches = this.minimalService.getBranches(minimalCriteria);

        return ResponseEntity.ok(
                BaseResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message(HttpStatus.OK.getReasonPhrase())
                        .data(branches)
                        .build()
        );
    }

    @Override
    public ResponseEntity<?> getScreenings(MinimalCriteria minimalCriteria) {
        PageableDTO<?> screenings = this.minimalService.getScreenings(minimalCriteria);

        return ResponseEntity.ok(
                BaseResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message(HttpStatus.OK.getReasonPhrase())
                        .data(screenings)
                        .build()
        );
    }

    @Override
    public ResponseEntity<BaseResponse<PageableDTO<List<MinimalDTO>>>> getCinemas(MinimalCriteria minimalCriteria) {
        PageableDTO<List<MinimalDTO>> cinemas = this.minimalService.getCinemas(minimalCriteria);

        return ResponseEntity.ok(
                BaseResponse.<PageableDTO<List<MinimalDTO>>>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message(HttpStatus.OK.getReasonPhrase())
                        .data(cinemas)
                        .build()
        );
    }

    @Override
    public ResponseEntity<BaseResponse<PageableDTO<List<MinimalDTO>>>> getDistricts(MinimalCriteria minimalCriteria) {
        PageableDTO<List<MinimalDTO>> districts = this.minimalService.getDistricts(minimalCriteria);

        return ResponseEntity.ok(
                BaseResponse.<PageableDTO<List<MinimalDTO>>>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message(HttpStatus.OK.getReasonPhrase())
                        .data(districts)
                        .build()
        );
    }
}

