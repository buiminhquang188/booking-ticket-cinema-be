package org.cybersoft.bookingticketcinemabe.payload.request;

public record HallUpdateSeat(
        Integer id,
        Integer seatColumn,
        String seatRow,
        Integer seatNumber,
        String seatType,
        String seatPrice,
        Boolean isActive
) {
}
