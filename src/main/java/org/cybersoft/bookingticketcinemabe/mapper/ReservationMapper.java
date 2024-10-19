package org.cybersoft.bookingticketcinemabe.mapper;

import org.cybersoft.bookingticketcinemabe.dto.ReservationDTO;
import org.cybersoft.bookingticketcinemabe.entity.ReservationEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",
        uses = {
                HallMapper.class,
                UserMapper.class,
                ScreeningMapper.class
        })
public interface ReservationMapper extends EntityMapper<ReservationDTO, ReservationEntity> {
}
