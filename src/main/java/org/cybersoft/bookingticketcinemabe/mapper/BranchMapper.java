package org.cybersoft.bookingticketcinemabe.mapper;

import org.cybersoft.bookingticketcinemabe.dto.BranchDTO;
import org.cybersoft.bookingticketcinemabe.entity.BranchEntity;
import org.cybersoft.bookingticketcinemabe.payload.request.BranchCreationRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",
        uses = {
                DistrictMapper.class,
                HallMapper.class,
                MovieDetailMapper.class
        }
)
public interface BranchMapper extends EntityMapper<BranchDTO, BranchEntity> {
    BranchEntity toEntity(BranchCreationRequest request);

}
