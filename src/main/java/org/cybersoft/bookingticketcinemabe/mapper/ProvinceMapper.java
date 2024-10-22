package org.cybersoft.bookingticketcinemabe.mapper;

import org.cybersoft.bookingticketcinemabe.dto.ProvinceDTO;
import org.cybersoft.bookingticketcinemabe.entity.ProvinceEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProvinceMapper extends EntityMapper<ProvinceDTO, ProvinceEntity> {

}
