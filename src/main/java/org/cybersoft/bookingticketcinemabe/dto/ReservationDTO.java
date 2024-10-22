package org.cybersoft.bookingticketcinemabe.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cybersoft.bookingticketcinemabe.dto.screening.ScreeningDTO;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDTO {

    private Integer id;

    private LocalDateTime timeReservation;

    private String status;

    private ScreeningDTO screening;

    private UserDTO user;

    private List<SeatReservationDTO> seatReservations;

}