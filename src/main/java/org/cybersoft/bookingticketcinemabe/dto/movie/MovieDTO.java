package org.cybersoft.bookingticketcinemabe.dto.movie;

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

    private LocalDateTime startDate;

    private String movieName;

    private String poster;

    private String trailer;

    private List<BranchMinimalDTO> branches;

    private List<ScreeningDTO> screenings;
}
