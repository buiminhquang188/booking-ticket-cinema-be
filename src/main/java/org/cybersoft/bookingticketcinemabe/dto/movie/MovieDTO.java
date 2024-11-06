package org.cybersoft.bookingticketcinemabe.dto.movie;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cybersoft.bookingticketcinemabe.dto.branch.BranchMinimalDTO;
import org.cybersoft.bookingticketcinemabe.dto.screening.ScreeningDTO;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieDTO {
    private Integer id;

    private Byte rating;

    private Integer time;  // Time in minutes or another appropriate unit

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime startDate;

    private String name;

    private String poster;

    private String trailer;

    private List<BranchMinimalDTO> branches;

    private List<ScreeningDTO> screenings;
}
