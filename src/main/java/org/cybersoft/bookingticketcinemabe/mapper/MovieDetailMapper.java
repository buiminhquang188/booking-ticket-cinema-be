package org.cybersoft.bookingticketcinemabe.mapper;

import org.cybersoft.bookingticketcinemabe.dto.MovieDetailDTO;
import org.cybersoft.bookingticketcinemabe.entity.MovieEntity;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface MovieDetailMapper extends EntityMapper<MovieDetailDTO, MovieEntity> {
    Set<MovieDetailDTO> toDTOs(Set<MovieEntity> entities);
}
