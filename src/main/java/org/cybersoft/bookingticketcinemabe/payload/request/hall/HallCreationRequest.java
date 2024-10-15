package org.cybersoft.bookingticketcinemabe.payload.request.hall;

import java.util.List;

public record HallCreationRequest(
        String name,
        Integer totalSeats,
        Integer branchId,
        List<HallCreateSeat> seats
) {
}
