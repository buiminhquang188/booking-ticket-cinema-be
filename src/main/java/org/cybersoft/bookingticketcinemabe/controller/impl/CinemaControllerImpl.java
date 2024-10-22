package org.cybersoft.bookingticketcinemabe.controller.impl;

import lombok.RequiredArgsConstructor;
import org.cybersoft.bookingticketcinemabe.controller.CinemaController;
import org.cybersoft.bookingticketcinemabe.dto.PageableDTO;
import org.cybersoft.bookingticketcinemabe.dto.cinema.CinemaDetailDTO;
import org.cybersoft.bookingticketcinemabe.payload.request.CinemaCreationRequest;
import org.cybersoft.bookingticketcinemabe.payload.request.CinemaCriteria;
import org.cybersoft.bookingticketcinemabe.payload.request.CinemaUpdateRequest;
import org.cybersoft.bookingticketcinemabe.payload.response.BaseResponse;
import org.cybersoft.bookingticketcinemabe.service.CinemaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CinemaControllerImpl implements CinemaController {
    private final CinemaService cinemaService;

    @Override
    public ResponseEntity<BaseResponse<?>> getCinemas(CinemaCriteria cinemaCriteria) {
        PageableDTO<?> cinema = this.cinemaService.getCinemas(cinemaCriteria);

        return ResponseEntity.ok(
                BaseResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message(HttpStatus.OK.getReasonPhrase())
                        .data(cinema)
                        .build()
        );
    }

    @Override
    public ResponseEntity<?> getCinema(int id) {
        CinemaDetailDTO cinema = this.cinemaService.getCinema(id);

        return ResponseEntity.ok(
                BaseResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message(HttpStatus.OK.getReasonPhrase())
                        .data(cinema)
                        .build()
        );
    }

    @Override
    public ResponseEntity<?> createCinema(CinemaCreationRequest cinemaCreationRequest) {
        CinemaDetailDTO cinema = this.cinemaService.createCinema(cinemaCreationRequest);

        return ResponseEntity.ok(
                BaseResponse.builder()
                        .statusCode(HttpStatus.CREATED.value())
                        .message(HttpStatus.CREATED.getReasonPhrase())
                        .data(cinema)
                        .build()
        );
    }

    @Override
    public ResponseEntity<?> updateCinema(Integer id, CinemaUpdateRequest cinemaUpdateRequest) {
        CinemaDetailDTO cinema = this.cinemaService.updateCinema(id, cinemaUpdateRequest);

        return ResponseEntity.ok(
                BaseResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message(HttpStatus.OK.getReasonPhrase())
                        .data(cinema)
                        .build()
        );
    }

    @Override
    public ResponseEntity<?> deleteCinema(Integer id) {
        this.cinemaService.deleteCinema(id);

        return ResponseEntity.ok(
                BaseResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message(HttpStatus.OK.getReasonPhrase())
                        .data(null)
                        .build()
        );
    }
}
