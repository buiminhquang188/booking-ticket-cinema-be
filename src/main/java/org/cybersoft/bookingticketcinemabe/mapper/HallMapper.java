package org.cybersoft.bookingticketcinemabe.mapper;

import org.cybersoft.bookingticketcinemabe.dto.HallDTO;
import org.cybersoft.bookingticketcinemabe.dto.MovieDTO;
import org.cybersoft.bookingticketcinemabe.entity.HallEntity;
import org.cybersoft.bookingticketcinemabe.entity.MovieEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HallMapper extends EntityMapper<HallDTO, HallEntity> {

}
