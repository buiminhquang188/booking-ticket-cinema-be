package org.cybersoft.bookingticketcinemabe.mapper.branch;

import org.cybersoft.bookingticketcinemabe.dto.branch.BranchDTO;
import org.cybersoft.bookingticketcinemabe.entity.BranchEntity;
import org.cybersoft.bookingticketcinemabe.mapper.DistrictMapper;
import org.cybersoft.bookingticketcinemabe.mapper.EntityMapper;
import org.cybersoft.bookingticketcinemabe.mapper.HallMapper;
import org.cybersoft.bookingticketcinemabe.mapper.movie.MovieDetailMapper;
import org.cybersoft.bookingticketcinemabe.payload.request.branch.BranchCreationRequest;
import org.cybersoft.bookingticketcinemabe.payload.request.branch.BranchUpdateRequest;
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
