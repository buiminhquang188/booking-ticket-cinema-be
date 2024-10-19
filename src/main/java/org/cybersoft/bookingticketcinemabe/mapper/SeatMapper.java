package org.cybersoft.bookingticketcinemabe.mapper;

import org.cybersoft.bookingticketcinemabe.dto.SeatDTO;
import org.cybersoft.bookingticketcinemabe.entity.SeatEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",
        uses = {
                HallMapper.class,
                SeatReservationMapper.class
        })
public interface SeatMapper extends EntityMapper<SeatDTO, SeatEntity> {
}
