package org.cybersoft.bookingticketcinemabe.mapper.movie;

import org.cybersoft.bookingticketcinemabe.dto.branch.BranchMinimalDTO;
import org.cybersoft.bookingticketcinemabe.dto.movie.MovieDTO;
import org.cybersoft.bookingticketcinemabe.entity.MovieEntity;
import org.cybersoft.bookingticketcinemabe.mapper.EntityMapper;
import org.cybersoft.bookingticketcinemabe.mapper.screening.ScreeningMapper;
import org.cybersoft.bookingticketcinemabe.payload.request.MovieCreationRequest;
import org.cybersoft.bookingticketcinemabe.payload.request.MovieUpdateRequest;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        uses = {BranchMinimalDTO.class,
                ScreeningMapper.class}
)
public interface MovieMapper extends EntityMapper<MovieDTO, MovieEntity> {
    MovieEntity toEntity(MovieCreationRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(@MappingTarget MovieEntity movie, MovieUpdateRequest request);
}
