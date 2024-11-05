package org.cybersoft.bookingticketcinemabe.dto.screening;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cybersoft.bookingticketcinemabe.dto.hall.HallDetailDTO;
import org.cybersoft.bookingticketcinemabe.dto.movie.MovieDetailDTO;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScreeningDTO {
    private Integer id;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private HallDetailDTO hall;

    private MovieDetailDTO movie;
}
