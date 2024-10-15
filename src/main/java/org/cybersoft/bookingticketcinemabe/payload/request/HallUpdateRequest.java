package org.cybersoft.bookingticketcinemabe.payload.request;

import java.util.List;

public record HallUpdateRequest(
        String name,
        Integer totalSeats,
        Integer branchId,
        List<HallUpdateSeat> seats
) {
}
