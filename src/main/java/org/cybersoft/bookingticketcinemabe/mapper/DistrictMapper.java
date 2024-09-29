package org.cybersoft.bookingticketcinemabe.mapper;

import org.cybersoft.bookingticketcinemabe.dto.DistrictDTO;
import org.cybersoft.bookingticketcinemabe.entity.DistrictEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DistrictMapper extends EntityMapper<DistrictDTO, DistrictEntity> {

}
