package org.cybersoft.bookingticketcinemabe.mapper;

import org.cybersoft.bookingticketcinemabe.dto.hall.HallDTO;
import org.cybersoft.bookingticketcinemabe.dto.hall.HallDetailDTO;
import org.cybersoft.bookingticketcinemabe.dto.hall.HallDetailSeatLayoutDTO;
import org.cybersoft.bookingticketcinemabe.dto.seat.SeatTypeDTO;
import org.cybersoft.bookingticketcinemabe.entity.HallEntity;
import org.cybersoft.bookingticketcinemabe.entity.SeatEntity;
import org.cybersoft.bookingticketcinemabe.entity.SeatTypeEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HallMapper extends EntityMapper<HallDTO, HallEntity> {
    HallDetailDTO toHallDetailDto(HallEntity hallEntity);

    HallDetailSeatLayoutDTO toHallDetailSeatLayoutDto(SeatEntity seatEntity);

    SeatTypeDTO toSeatTypeDTO(SeatTypeEntity seatTypeEntity);
}
