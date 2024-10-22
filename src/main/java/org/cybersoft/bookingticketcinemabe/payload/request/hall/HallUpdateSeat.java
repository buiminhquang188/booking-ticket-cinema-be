package org.cybersoft.bookingticketcinemabe.payload.request.hall;

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
