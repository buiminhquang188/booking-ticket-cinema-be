package org.cybersoft.bookingticketcinemabe.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SeatDTO {

    private Integer id;

    private Boolean isActive;

    private Integer seatColumn;

    private String seatRow;

    private Integer seatNumber;

    private String seatCode;

    private HallDTO hall;

    private List<SeatReservationDTO> seatReservations;

}
