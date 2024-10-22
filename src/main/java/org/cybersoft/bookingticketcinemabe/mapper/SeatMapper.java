package org.cybersoft.bookingticketcinemabe.mapper;

import org.cybersoft.bookingticketcinemabe.dto.SeatDTO;
import org.cybersoft.bookingticketcinemabe.dto.SeatDetailDTO;
import org.cybersoft.bookingticketcinemabe.dto.SeatHallDTO;
import org.cybersoft.bookingticketcinemabe.entity.SeatEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",
        uses = {
                HallMapper.class,
                SeatReservationMapper.class
        })
public interface SeatMapper extends EntityMapper<SeatDTO, SeatEntity> {
    SeatDetailDTO toSeatDetailDTO(SeatEntity seat);

    @Mapping(target = "id", source = "hall.id")
    @Mapping(target = "name", source = "hall.name")
    SeatHallDTO toSeatHallDTO(SeatEntity seat);
}
