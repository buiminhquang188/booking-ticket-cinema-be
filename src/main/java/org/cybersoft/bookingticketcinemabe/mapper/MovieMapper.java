package org.cybersoft.bookingticketcinemabe.mapper;

import org.cybersoft.bookingticketcinemabe.dto.MovieDTO;
import org.cybersoft.bookingticketcinemabe.entity.MovieEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",
        uses = {BranchDetailMapper.class,
                ScreeningMapper.class}
)
public interface MovieMapper extends EntityMapper<MovieDTO, MovieEntity> {
}
