package org.cybersoft.bookingticketcinemabe.controller;

import org.cybersoft.bookingticketcinemabe.dto.PageableDTO;
import org.cybersoft.bookingticketcinemabe.dto.SeatDetailDTO;
import org.cybersoft.bookingticketcinemabe.payload.request.seat.SeatCriteria;
import org.cybersoft.bookingticketcinemabe.payload.response.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping
public interface SeatController {
    @GetMapping("/seats")
    ResponseEntity<BaseResponse<PageableDTO<List<SeatDetailDTO>>>> getSeats(SeatCriteria seatCriteria);

    @GetMapping("/seat/{id}")
    ResponseEntity<BaseResponse<SeatDetailDTO>> getSeat(@PathVariable Integer id);
}
