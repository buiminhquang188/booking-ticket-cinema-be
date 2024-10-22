package org.cybersoft.bookingticketcinemabe.dto.screening;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cybersoft.bookingticketcinemabe.dto.ReservationDTO;
import org.cybersoft.bookingticketcinemabe.dto.SeatReservationDTO;
import org.cybersoft.bookingticketcinemabe.dto.hall.HallDTO;
import org.cybersoft.bookingticketcinemabe.dto.movie.MovieDetailDTO;

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
