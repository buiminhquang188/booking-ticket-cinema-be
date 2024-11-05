package org.cybersoft.bookingticketcinemabe.mapper.screening;

import org.cybersoft.bookingticketcinemabe.dto.screening.ScreeningDTO;
import org.cybersoft.bookingticketcinemabe.dto.screening.ScreeningDetailDTO;
import org.cybersoft.bookingticketcinemabe.dto.screening.ScreeningDetailSeatLayoutDTO;
import org.cybersoft.bookingticketcinemabe.entity.ScreeningEntity;
import org.cybersoft.bookingticketcinemabe.entity.ScreeningSeatEntity;
import org.cybersoft.bookingticketcinemabe.mapper.EntityMapper;
import org.cybersoft.bookingticketcinemabe.mapper.HallMapper;
import org.cybersoft.bookingticketcinemabe.mapper.ScreeningSeatMapper;
import org.cybersoft.bookingticketcinemabe.mapper.movie.MovieDetailMapper;
import org.cybersoft.bookingticketcinemabe.payload.request.screening.ScreeningCreationRequest;
import org.cybersoft.bookingticketcinemabe.payload.request.screening.ScreeningUpdateRequest;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        uses = {
                HallMapper.class,
                MovieDetailMapper.class,
                ScreeningSeatMapper.class
        })
public interface ScreeningMapper extends EntityMapper<ScreeningDTO, ScreeningEntity> {
    ScreeningEntity toEntity(ScreeningCreationRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(@MappingTarget ScreeningEntity screening, ScreeningUpdateRequest request);

    ScreeningDetailSeatLayoutDTO toScreeningDetailSeatLayoutDto(ScreeningSeatEntity screeningSeatEntity);

    @Mapping(target = "screeningSeats", ignore = true)
    ScreeningDetailDTO toScreeningDetailDto(ScreeningEntity screeningEntity);
}
