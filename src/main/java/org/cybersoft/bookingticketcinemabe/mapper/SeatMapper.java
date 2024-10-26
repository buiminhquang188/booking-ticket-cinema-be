package org.cybersoft.bookingticketcinemabe.mapper;

import org.cybersoft.bookingticketcinemabe.dto.seat.SeatDTO;
import org.cybersoft.bookingticketcinemabe.dto.seat.SeatDetailDTO;
import org.cybersoft.bookingticketcinemabe.dto.seat.SeatHallDTO;
import org.cybersoft.bookingticketcinemabe.entity.ScreeningSeatEntity;
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

    ScreeningSeatEntity toScreeningSeatEntity(SeatEntity seat);
}
