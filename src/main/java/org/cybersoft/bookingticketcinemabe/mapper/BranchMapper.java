package org.cybersoft.bookingticketcinemabe.mapper;

import org.cybersoft.bookingticketcinemabe.dto.BranchDTO;
import org.cybersoft.bookingticketcinemabe.entity.BranchEntity;
import org.cybersoft.bookingticketcinemabe.payload.request.BranchCreationRequest;
import org.cybersoft.bookingticketcinemabe.payload.request.BranchUpdateRequest;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        uses = {
                DistrictMapper.class,
                HallMapper.class,
                MovieDetailMapper.class
        }
)
public interface BranchMapper extends EntityMapper<BranchDTO, BranchEntity> {
    BranchEntity toEntity(BranchCreationRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(@MappingTarget BranchEntity branch, BranchUpdateRequest request);

}
