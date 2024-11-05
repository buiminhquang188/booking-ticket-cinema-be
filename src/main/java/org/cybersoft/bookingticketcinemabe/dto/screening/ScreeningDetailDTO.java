package org.cybersoft.bookingticketcinemabe.dto.screening;

import lombok.Data;
import org.cybersoft.bookingticketcinemabe.dto.hall.HallDetailDTO;
import org.cybersoft.bookingticketcinemabe.dto.movie.MovieDetailDTO;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ScreeningDetailDTO {
    private Integer id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private HallDetailDTO hall;
    private MovieDetailDTO movie;
    private List<List<ScreeningDetailSeatLayoutDTO>> screeningSeats;
}
