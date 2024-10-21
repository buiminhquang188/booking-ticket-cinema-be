package org.cybersoft.bookingticketcinemabe.controller.impl;

import lombok.RequiredArgsConstructor;
import org.cybersoft.bookingticketcinemabe.controller.ScreeningController;
import org.cybersoft.bookingticketcinemabe.dto.PageableDTO;
import org.cybersoft.bookingticketcinemabe.dto.screening.ScreeningDTO;
import org.cybersoft.bookingticketcinemabe.dto.screening.ScreeningMinimalDTO;
import org.cybersoft.bookingticketcinemabe.payload.request.ScreeningCreationRequest;
import org.cybersoft.bookingticketcinemabe.payload.request.ScreeningUpdateRequest;
import org.cybersoft.bookingticketcinemabe.payload.response.BaseResponse;
import org.cybersoft.bookingticketcinemabe.service.ScreeningService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ScreeningControllerImpl implements ScreeningController {
    private final ScreeningService screeningService;

    @Override
    public ResponseEntity<?> getScreenings(int pageNo, int pageLimit, String sortBy) {
        PageableDTO<?> screenings = screeningService.getScreenings(pageNo, pageLimit, sortBy);
        return ResponseEntity.ok(BaseResponse.builder()
                .statusCode(HttpStatus.OK.value())
                .message(HttpStatus.OK.getReasonPhrase())
                .data(screenings)
                .build()
        );
    }

    @Override
    public ResponseEntity<?> getScreening(Integer id) {
        ScreeningDTO screening = screeningService.getScreening(id);
        return ResponseEntity.ok(
                BaseResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message(HttpStatus.OK.getReasonPhrase())
                        .data(screening)
                        .build()
        );
    }

    @Override
    public ResponseEntity<?> createScreening(ScreeningCreationRequest request) {
        ScreeningMinimalDTO screening = screeningService.createScreening(request);
        return ResponseEntity.ok(
                BaseResponse.builder()
                        .statusCode(HttpStatus.CREATED.value())
                        .message(HttpStatus.CREATED.getReasonPhrase())
                        .data(screening)
                        .build()
        );
    }

    @Override
    public ResponseEntity<?> updateScreening(Integer id, ScreeningUpdateRequest request) {
        ScreeningDTO screening = screeningService.updateScreening(id, request);
        return ResponseEntity.ok(
                BaseResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message(HttpStatus.OK.getReasonPhrase())
                        .data(screening)
                        .build()
        );
    }

    @Override
    public ResponseEntity<?> deleteScreening(Integer id) {
        screeningService.deleteScreening(id);
        return ResponseEntity.ok(
                BaseResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message(HttpStatus.OK.getReasonPhrase())
                        .data(null)
                        .build()
        );
    }
}