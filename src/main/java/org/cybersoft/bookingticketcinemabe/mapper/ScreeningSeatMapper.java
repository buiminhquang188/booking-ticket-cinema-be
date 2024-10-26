package org.cybersoft.bookingticketcinemabe.mapper;

import org.cybersoft.bookingticketcinemabe.dto.ScreeningSeatDTO;
import org.cybersoft.bookingticketcinemabe.entity.ScreeningSeatEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ScreeningSeatMapper extends EntityMapper<ScreeningSeatDTO, ScreeningSeatEntity> {
}
