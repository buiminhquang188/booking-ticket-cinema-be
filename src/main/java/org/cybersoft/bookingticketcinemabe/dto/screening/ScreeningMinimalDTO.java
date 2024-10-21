package org.cybersoft.bookingticketcinemabe.dto.screening;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cybersoft.bookingticketcinemabe.dto.movie.MovieDetailDTO;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScreeningMinimalDTO {

    private MovieDetailDTO movie;

    private LocalDateTime startTime;

    private LocalDateTime endTime;
}
