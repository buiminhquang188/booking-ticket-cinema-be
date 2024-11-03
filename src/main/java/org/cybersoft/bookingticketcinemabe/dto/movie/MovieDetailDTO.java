package org.cybersoft.bookingticketcinemabe.dto.movie;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieDetailDTO {
    private Integer id;

    private Byte rating;

    private Integer time;  // Time in minutes unit

    private LocalDateTime startDate;

    private String name;


}
