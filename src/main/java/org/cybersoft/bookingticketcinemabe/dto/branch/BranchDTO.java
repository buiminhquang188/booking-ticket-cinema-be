package org.cybersoft.bookingticketcinemabe.dto.branch;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cybersoft.bookingticketcinemabe.dto.DistrictDTO;
import org.cybersoft.bookingticketcinemabe.dto.cinema.CinemaDetailBranchDTO;
import org.cybersoft.bookingticketcinemabe.dto.hall.HallDTO;
import org.cybersoft.bookingticketcinemabe.dto.movie.MovieDetailDTO;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BranchDTO {
    private Integer id;

    private BigDecimal distance;

    private BigDecimal lat;

    private BigDecimal lon;

    private BigDecimal rating;

    private Integer totalCineplexHall;

    private String address;

    private String avatar;

    private String link;

    private String logo;

    private String name;

    private CinemaDetailBranchDTO cinema;

    private DistrictDTO district;

    private List<HallDTO> halls;

    private Set<MovieDetailDTO> movies;
}
