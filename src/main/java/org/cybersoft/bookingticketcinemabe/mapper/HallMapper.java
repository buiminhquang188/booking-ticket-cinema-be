package org.cybersoft.bookingticketcinemabe.mapper;

import org.cybersoft.bookingticketcinemabe.dto.HallDTO;
import org.cybersoft.bookingticketcinemabe.dto.HallDetailDTO;
import org.cybersoft.bookingticketcinemabe.dto.HallDetailSeatLayoutDTO;
import org.cybersoft.bookingticketcinemabe.entity.HallEntity;
import org.cybersoft.bookingticketcinemabe.entity.SeatEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HallMapper extends EntityMapper<HallDTO, HallEntity> {
    HallDetailDTO toHallDetailDto(HallEntity hallEntity);

    HallDetailSeatLayoutDTO toHallDetailSeatLayoutDto(SeatEntity seatEntity);
}
