package org.cybersoft.bookingticketcinemabe.mapper.screening;

import org.cybersoft.bookingticketcinemabe.dto.screening.ScreeningDTO;
import org.cybersoft.bookingticketcinemabe.entity.ScreeningEntity;
import org.cybersoft.bookingticketcinemabe.mapper.EntityMapper;
import org.cybersoft.bookingticketcinemabe.mapper.HallMapper;
import org.cybersoft.bookingticketcinemabe.mapper.movie.MovieDetailMapper;
import org.cybersoft.bookingticketcinemabe.payload.request.ScreeningCreationRequest;
import org.cybersoft.bookingticketcinemabe.payload.request.ScreeningUpdateRequest;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        uses = {
                HallMapper.class,
                MovieDetailMapper.class
        })
public interface ScreeningMapper extends EntityMapper<ScreeningDTO, ScreeningEntity> {
    ScreeningEntity toEntity(ScreeningCreationRequest request);

    ScreeningEntity clone(ScreeningEntity screening);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(@MappingTarget ScreeningEntity screening, ScreeningUpdateRequest request);
}
