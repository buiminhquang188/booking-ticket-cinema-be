package org.cybersoft.bookingticketcinemabe.mapper;

import org.cybersoft.bookingticketcinemabe.dto.MinimalDTO;
import org.cybersoft.bookingticketcinemabe.dto.branch.BranchMinimalDTO;
import org.cybersoft.bookingticketcinemabe.dto.movie.MovieDetailDTO;
import org.cybersoft.bookingticketcinemabe.dto.screening.ScreeningMinimalDTO;
import org.cybersoft.bookingticketcinemabe.entity.*;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MinimalMapper {


    ScreeningMinimalDTO toScreeningMinimalDTO(ScreeningEntity screeningEntity);

    BranchMinimalDTO toBranchMinimalDTO(BranchEntity branchEntity);

    MovieDetailDTO toMovieDetailDTO(MovieEntity movieEntity);

    MinimalDTO toCinemaMinimalDTO(CinemaEntity cinemaEntity);

    MinimalDTO toDistrictMinimalDTO(DistrictEntity districtEntity);
}
