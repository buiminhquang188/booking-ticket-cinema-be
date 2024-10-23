package org.cybersoft.bookingticketcinemabe.dto.hall;

import lombok.Data;
import org.cybersoft.bookingticketcinemabe.dto.seat.SeatTypeDTO;

@Data
public class HallDetailSeatLayoutDTO {
    private Integer id;
    private String seatRow;
    private Integer seatColumn;
    private Integer seatNumber;
    private String seatCode;
    private SeatTypeDTO seatType;
    private Double price;
    private Boolean isActive;
}
