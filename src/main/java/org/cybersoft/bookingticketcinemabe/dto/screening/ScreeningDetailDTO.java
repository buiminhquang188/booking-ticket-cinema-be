package org.cybersoft.bookingticketcinemabe.dto.screening;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.cybersoft.bookingticketcinemabe.dto.hall.HallDetailDTO;
import org.cybersoft.bookingticketcinemabe.dto.movie.MovieDetailDTO;
import org.cybersoft.bookingticketcinemabe.enums.ScreeningStatus;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ScreeningDetailDTO {
    private Integer id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime startTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime endTime;
    private HallDetailDTO hall;
    private MovieDetailDTO movie;
    private List<List<ScreeningDetailSeatLayoutDTO>> screeningSeats;
    private ScreeningStatus status;
}
