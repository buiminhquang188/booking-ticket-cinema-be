package org.cybersoft.bookingticketcinemabe.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cybersoft.bookingticketcinemabe.entity.SeatEntity;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SeatReservationDTO {

    private int id;

    private boolean isReserved;

    private double price;

    private SeatEntity seat;

    private ReservationDTO reservation;

    private ScreeningDTO screening;

}
