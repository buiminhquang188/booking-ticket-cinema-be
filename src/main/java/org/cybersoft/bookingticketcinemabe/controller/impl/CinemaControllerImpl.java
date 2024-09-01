package org.cybersoft.bookingticketcinemabe.controller.impl;

import org.cybersoft.bookingticketcinemabe.controller.CinemaController;
import org.cybersoft.bookingticketcinemabe.payload.response.BaseResponse;
import org.cybersoft.bookingticketcinemabe.service.CinemaService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CinemaControllerImpl implements CinemaController {
    private final CinemaService cinemaService;

    public CinemaControllerImpl(CinemaService cinemaService) {
        this.cinemaService = cinemaService;
    }

    @Override
    public ResponseEntity<BaseResponse<?>> getCinemas(int pageNo, int pageSize, String name) {
        Page<?> cinema = this.cinemaService.getCinemas(pageNo, pageSize, name);

        return ResponseEntity.ok(
                BaseResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message(HttpStatus.OK.getReasonPhrase())
                        .data(cinema)
                        .build()
        );
    }
}
