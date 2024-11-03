package org.cybersoft.bookingticketcinemabe.mapper;

import org.cybersoft.bookingticketcinemabe.dto.minimal.MinimalDTO;
import org.cybersoft.bookingticketcinemabe.dto.branch.BranchMinimalDTO;
import org.cybersoft.bookingticketcinemabe.dto.movie.MovieDetailDTO;
import org.cybersoft.bookingticketcinemabe.dto.screening.ScreeningMinimalDTO;
import org.cybersoft.bookingticketcinemabe.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MinimalMapper {


    @Mapping(target = "totalSeat", expression = "java(screening.getScreeningSeats().size())")
    ScreeningMinimalDTO toScreeningMinimalDTO(ScreeningEntity screening);

    BranchMinimalDTO toBranchMinimalDTO(BranchEntity branch);

    MovieDetailDTO toMovieDetailDTO(MovieEntity movie);

    MinimalDTO toCinemaMinimalDTO(CinemaEntity cinemaEntity);

    MinimalDTO toDistrictMinimalDTO(DistrictEntity districtEntity);
}
