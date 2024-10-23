package org.cybersoft.bookingticketcinemabe.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cybersoft.bookingticketcinemabe.dto.screening.ScreeningDTO;
import org.cybersoft.bookingticketcinemabe.entity.SeatEntity;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SeatReservationDTO {
    private Integer id;
    private Double price;
    private SeatEntity seat;
    private ReservationDTO reservation;
    private ScreeningDTO screening;
}
