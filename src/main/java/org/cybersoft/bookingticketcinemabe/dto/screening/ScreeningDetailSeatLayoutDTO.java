package org.cybersoft.bookingticketcinemabe.dto.screening;

import lombok.Data;
import org.cybersoft.bookingticketcinemabe.dto.seat.SeatTypeDTO;

@Data
public class ScreeningDetailSeatLayoutDTO {
    private Integer id;
    private String seatRow;
    private Integer seatColumn;
    private Integer seatNumber;
    private String seatCode;
    private SeatTypeDTO seatType;
    private Double price;
    private Boolean isActive;
    private Boolean isBooked;
}
