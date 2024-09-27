package org.cybersoft.bookingticketcinemabe.mapper;

import org.cybersoft.bookingticketcinemabe.entity.CinemaEntity;
import org.cybersoft.bookingticketcinemabe.entity.CinemaProvinceEntity;
import org.cybersoft.bookingticketcinemabe.entity.ProvinceEntity;
import org.cybersoft.bookingticketcinemabe.entity.key.IdCinemaProvince;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface CinemaProvinceMapper {
    @Mapping(target = "id", source = "idCinemaProvince")
    CinemaProvinceEntity cinemaAndProvinceToCinemaProvinceEntity(CinemaEntity cinema, ProvinceEntity province, IdCinemaProvince idCinemaProvince);

    @Mapping(target = "cinemaId", source = "cinema.id")
    @Mapping(target = "provinceId", source = "province.id")
    IdCinemaProvince toIdCinemaProvince(CinemaEntity cinema, ProvinceEntity province);
}
