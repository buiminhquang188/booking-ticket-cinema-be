package org.cybersoft.bookingticketcinemabe.controller.impl;

import lombok.AllArgsConstructor;
import org.cybersoft.bookingticketcinemabe.controller.SeatController;
import org.cybersoft.bookingticketcinemabe.dto.PageableDTO;
import org.cybersoft.bookingticketcinemabe.dto.seat.SeatDetailDTO;
import org.cybersoft.bookingticketcinemabe.payload.request.seat.SeatCriteria;
import org.cybersoft.bookingticketcinemabe.payload.request.seat.SeatUpdateRequest;
import org.cybersoft.bookingticketcinemabe.payload.response.BaseResponse;
import org.cybersoft.bookingticketcinemabe.service.SeatService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class SeatControllerImpl implements SeatController {
    private final SeatService seatService;

    @Override
    public ResponseEntity<BaseResponse<PageableDTO<List<SeatDetailDTO>>>> getSeats(SeatCriteria seatCriteria) {
        PageableDTO<List<SeatDetailDTO>> seat = this.seatService.getSeats(seatCriteria);

        return ResponseEntity.ok(
                BaseResponse.<PageableDTO<List<SeatDetailDTO>>>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message(HttpStatus.OK.getReasonPhrase())
                        .data(seat)
                        .build()
        );
    }

    @Override
    public ResponseEntity<BaseResponse<SeatDetailDTO>> getSeat(Integer id) {
        SeatDetailDTO seat = this.seatService.getSeat(id);

        return ResponseEntity.ok(
                BaseResponse.<SeatDetailDTO>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message(HttpStatus.OK.getReasonPhrase())
                        .data(seat)
                        .build()
        );
    }

    @Override
    public ResponseEntity<BaseResponse<SeatDetailDTO>> updateSeat(Integer id, SeatUpdateRequest seatUpdateRequest) {
        SeatDetailDTO seatDetailDTO = this.seatService.updateSeat(id, seatUpdateRequest);

        return ResponseEntity.ok(
                BaseResponse.<SeatDetailDTO>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message(HttpStatus.OK.getReasonPhrase())
                        .data(seatDetailDTO)
                        .build()
        );
    }
}
