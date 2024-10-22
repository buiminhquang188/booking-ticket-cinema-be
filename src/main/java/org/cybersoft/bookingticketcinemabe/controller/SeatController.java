package org.cybersoft.bookingticketcinemabe.controller;

import jakarta.validation.Valid;
import org.cybersoft.bookingticketcinemabe.annotation.validator.ValidSeat;
import org.cybersoft.bookingticketcinemabe.dto.PageableDTO;
import org.cybersoft.bookingticketcinemabe.dto.seat.SeatDetailDTO;
import org.cybersoft.bookingticketcinemabe.payload.request.seat.SeatCriteria;
import org.cybersoft.bookingticketcinemabe.payload.request.seat.SeatUpdateRequest;
import org.cybersoft.bookingticketcinemabe.payload.response.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping
public interface SeatController {
    @GetMapping("/seats")
    ResponseEntity<BaseResponse<PageableDTO<List<SeatDetailDTO>>>> getSeats(SeatCriteria seatCriteria);

    @GetMapping("/seat/{id}")
    ResponseEntity<BaseResponse<SeatDetailDTO>> getSeat(@PathVariable Integer id);

    @PatchMapping("/seat/{id}")
    ResponseEntity<BaseResponse<SeatDetailDTO>> updateSeat(@PathVariable Integer id, @RequestBody @Valid @ValidSeat SeatUpdateRequest seatUpdateRequest
    );
}
