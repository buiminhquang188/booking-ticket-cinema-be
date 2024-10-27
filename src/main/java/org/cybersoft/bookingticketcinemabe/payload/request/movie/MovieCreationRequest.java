package org.cybersoft.bookingticketcinemabe.payload.request.movie;

import java.time.LocalDateTime;
import java.util.List;

public record MovieCreationRequest(Byte rating,
                                   Integer time,
                                   LocalDateTime startDate,
                                   String movieName,
                                   String poster,
                                   String trailer,
                                   List<Integer> branchIds,
                                   List<Integer> screeningIds) {
}
