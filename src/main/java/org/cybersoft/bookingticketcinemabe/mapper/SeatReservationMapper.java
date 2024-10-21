package org.cybersoft.bookingticketcinemabe.mapper;

import org.cybersoft.bookingticketcinemabe.dto.SeatReservationDTO;
import org.cybersoft.bookingticketcinemabe.entity.SeatReservationEntity;
import org.cybersoft.bookingticketcinemabe.mapper.screening.ScreeningMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",
        uses = {
                SeatMapper.class,
                ReservationMapper.class,
                ScreeningMapper.class
        })
public interface SeatReservationMapper extends EntityMapper<SeatReservationDTO, SeatReservationEntity> {
}
