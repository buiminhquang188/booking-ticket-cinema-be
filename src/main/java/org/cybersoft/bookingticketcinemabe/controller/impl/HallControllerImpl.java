package org.cybersoft.bookingticketcinemabe.controller.impl;

import lombok.AllArgsConstructor;
import org.cybersoft.bookingticketcinemabe.controller.HallController;
import org.cybersoft.bookingticketcinemabe.dto.HallDetailDTO;
import org.cybersoft.bookingticketcinemabe.dto.HallDetailSeatLayoutDTO;
import org.cybersoft.bookingticketcinemabe.dto.PageableDTO;
import org.cybersoft.bookingticketcinemabe.payload.request.hall.HallCreationRequest;
import org.cybersoft.bookingticketcinemabe.payload.request.hall.HallCriteria;
import org.cybersoft.bookingticketcinemabe.payload.request.hall.HallUpdateRequest;
import org.cybersoft.bookingticketcinemabe.payload.response.BaseResponse;
import org.cybersoft.bookingticketcinemabe.service.HallService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class HallControllerImpl implements HallController {
    private final HallService hallService;

    @Override
    public ResponseEntity<?> getHalls(HallCriteria hallCriteria) {
        PageableDTO<?> halls = this.hallService.getHalls(hallCriteria);

        return ResponseEntity.ok(
                BaseResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message(HttpStatus.OK.getReasonPhrase())
                        .data(halls)
                        .build()
        );
    }

    @Override
    public ResponseEntity<?> getHallById(Integer id) {
        HallDetailDTO hall = this.hallService.getHall(id);

        return ResponseEntity.ok(
                BaseResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message(HttpStatus.OK.getReasonPhrase())
                        .data(hall)
                        .build()
        );
    }

    @Override
    public ResponseEntity<?> createHall(HallCreationRequest request) {
        HallDetailDTO hall = this.hallService.createHall(request);

        return new ResponseEntity<>(
                BaseResponse.builder()
                        .statusCode(HttpStatus.CREATED.value())
                        .message(HttpStatus.CREATED.getReasonPhrase())
                        .data(hall)
                        .build(),
                HttpStatusCode.valueOf(HttpStatus.CREATED.value())
        );
    }

    @Override
    public ResponseEntity<?> updateHall(Integer id, HallUpdateRequest request) {
        HallDetailDTO hall = this.hallService.updateHall(id, request);
        return ResponseEntity.ok(
                BaseResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message(HttpStatus.OK.getReasonPhrase())
                        .data(hall)
                        .build()
        );
    }

    @Override
    public ResponseEntity<?> deleteHall(Integer id) {
        this.hallService.deleteHall(id);

        return ResponseEntity.ok(
                BaseResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message(HttpStatus.OK.getReasonPhrase())
                        .data(null)
                        .build()
        );
    }

    @Override
    public ResponseEntity<?> getSeatsLayout(Integer id) {
        List<List<HallDetailSeatLayoutDTO>> hallSeatLayout = this.hallService.getSeatsLayout(id);

        return ResponseEntity.ok(
                BaseResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message(HttpStatus.OK.getReasonPhrase())
                        .data(hallSeatLayout)
                        .build()
        );
    }
}
