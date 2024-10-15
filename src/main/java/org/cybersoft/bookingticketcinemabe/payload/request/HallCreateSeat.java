package org.cybersoft.bookingticketcinemabe.payload.request;

public record HallCreateSeat(
        Integer seatColumn,
        String seatRow,
        Integer seatNumber,
        String seatType,
        String seatPrice,
        Boolean isActive
) {
}
