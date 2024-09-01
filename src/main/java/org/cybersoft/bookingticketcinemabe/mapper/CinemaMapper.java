package org.cybersoft.bookingticketcinemabe.mapper;

import org.cybersoft.bookingticketcinemabe.dto.CinemaDTO;
import org.cybersoft.bookingticketcinemabe.entity.CinemaEntity;
import org.mapstruct.Mapper;

@Mapper
public interface CinemaMapper {
    CinemaDTO toCinemaDTO(CinemaEntity cinema);
}
