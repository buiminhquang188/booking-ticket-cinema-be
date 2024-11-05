package org.cybersoft.bookingticketcinemabe.payload.request.screening;

public record ScreeningUpdateSeat(
        Integer id,
        Integer seatColumn,
        String seatRow,
        Integer seatNumber,
        String seatType,
        Double seatPrice,
        Boolean isActive
) {
}
