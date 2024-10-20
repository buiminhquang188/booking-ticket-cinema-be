package org.cybersoft.bookingticketcinemabe.mapper.movie;

import org.cybersoft.bookingticketcinemabe.dto.movie.MovieDetailDTO;
import org.cybersoft.bookingticketcinemabe.entity.MovieEntity;
import org.cybersoft.bookingticketcinemabe.mapper.EntityMapper;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface MovieDetailMapper extends EntityMapper<MovieDetailDTO, MovieEntity> {
    Set<MovieDetailDTO> toDTOs(Set<MovieEntity> entities);
}
