package org.cybersoft.bookingticketcinemabe.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieDetailDTO {
    private Integer id;

    private String movieName;

}
