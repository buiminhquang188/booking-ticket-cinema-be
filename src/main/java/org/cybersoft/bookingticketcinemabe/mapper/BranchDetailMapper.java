package org.cybersoft.bookingticketcinemabe.mapper;

import org.cybersoft.bookingticketcinemabe.dto.BranchDetailDTO;
import org.cybersoft.bookingticketcinemabe.entity.BranchEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BranchDetailMapper {

    @Mapping(source = "district.name", target = "district")
    BranchDetailDTO toDTO(BranchEntity entity);
    
    @Mapping(target = "district.name", source = "district")
    BranchEntity toEntity(BranchDetailDTO dto);
}
