package org.cybersoft.bookingticketcinemabe.mapper;

import org.cybersoft.bookingticketcinemabe.dto.ScreeningDTO;
import org.cybersoft.bookingticketcinemabe.entity.ScreeningEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ScreeningMapper extends EntityMapper<ScreeningDTO, ScreeningEntity> {

}
