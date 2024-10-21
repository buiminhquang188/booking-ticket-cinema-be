package org.cybersoft.bookingticketcinemabe.dto;

import lombok.Data;

@Data
public class SeatDetailDTO {
    private Integer id;
    private String seatRow;
    private Integer seatColumn;
    private Integer seatNumber;
    private String seatCode;
    private SeatHallDTO hall;
}
