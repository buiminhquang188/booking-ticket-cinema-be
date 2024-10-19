package org.cybersoft.bookingticketcinemabe.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScreeningDTO {

    private Integer id;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private HallDTO hall;

    private MovieDetailDTO movie;

    private List<ReservationDTO> reservations;

    private List<SeatReservationDTO> seatReservations;

}
