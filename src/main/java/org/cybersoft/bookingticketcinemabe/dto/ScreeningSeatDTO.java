package org.cybersoft.bookingticketcinemabe.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cybersoft.bookingticketcinemabe.dto.seat.SeatTypeDTO;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScreeningSeatDTO {
    private Integer id;
    private Boolean isActive;
    private Boolean isBooked;
    private Integer seatColumn;
    private String seatRow;
    private Integer seatNumber;
    private String seatCode;
    private Double price;
    private SeatTypeDTO seatType;
}
