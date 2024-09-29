package org.cybersoft.bookingticketcinemabe.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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

//    private Set<BranchDTO> branches = new HashSet<>();
//
//    private List<ScreeningDTO> screenings;
}
