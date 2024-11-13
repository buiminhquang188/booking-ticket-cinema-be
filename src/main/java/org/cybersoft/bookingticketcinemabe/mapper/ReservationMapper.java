package org.cybersoft.bookingticketcinemabe.mapper;

import org.cybersoft.bookingticketcinemabe.dto.ReservationDTO;
import org.cybersoft.bookingticketcinemabe.dto.ReservationUserDTO;
import org.cybersoft.bookingticketcinemabe.entity.ReservationEntity;
import org.cybersoft.bookingticketcinemabe.entity.SeatReservationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring",
        uses = {
                MinimalMapper.class
        })
public interface ReservationMapper extends EntityMapper<ReservationDTO, ReservationEntity> {
    @Mapping(target = "seats", expression = "java(mapSeats(entity.getSeatReservations()))")
    ReservationUserDTO toReservationUserDto(ReservationEntity entity);

    default List<String> mapSeats(List<SeatReservationEntity> seatReservations) {
        return seatReservations.stream()
                .map(seatReservation -> seatReservation.getScreeningSeat().getSeatCode())
                .collect(Collectors.toList());
    }
}