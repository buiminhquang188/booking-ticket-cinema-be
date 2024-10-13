package org.cybersoft.bookingticketcinemabe.mapper;

import org.cybersoft.bookingticketcinemabe.dto.MovieDTO;
import org.cybersoft.bookingticketcinemabe.entity.MovieEntity;
import org.cybersoft.bookingticketcinemabe.payload.request.MovieCreationRequest;
import org.cybersoft.bookingticketcinemabe.payload.request.MovieUpdateRequest;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        uses = {BranchDetailMapper.class,
                ScreeningMapper.class}
)
public interface MovieMapper extends EntityMapper<MovieDTO, MovieEntity> {
    MovieEntity toEntity(MovieCreationRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(@MappingTarget MovieEntity movie, MovieUpdateRequest request);
}
