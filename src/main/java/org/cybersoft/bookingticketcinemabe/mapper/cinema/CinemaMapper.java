package org.cybersoft.bookingticketcinemabe.mapper.cinema;

import org.cybersoft.bookingticketcinemabe.dto.cinema.CinemaDTO;
import org.cybersoft.bookingticketcinemabe.dto.cinema.CinemaDetailDTO;
import org.cybersoft.bookingticketcinemabe.dto.cinema.CinemaDetailProvinceDTO;
import org.cybersoft.bookingticketcinemabe.entity.CinemaEntity;
import org.cybersoft.bookingticketcinemabe.entity.CinemaProvinceEntity;
import org.cybersoft.bookingticketcinemabe.mapper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface CinemaMapper extends EntityMapper<CinemaDTO, CinemaEntity> {
    @Mapping(source = "cinemaProvinces", target = "provinces")
    CinemaDetailDTO toCinemaDetailDto(CinemaEntity cinemaEntity);

    @Mapping(source = "province.id", target = "id")
    @Mapping(source = "province.name", target = "name")
    CinemaDetailProvinceDTO toCinemaProvinceDTO(CinemaProvinceEntity cinemaProvince);
}
