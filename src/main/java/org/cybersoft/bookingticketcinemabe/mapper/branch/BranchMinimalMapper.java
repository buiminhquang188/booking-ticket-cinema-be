package org.cybersoft.bookingticketcinemabe.mapper.branch;

import org.cybersoft.bookingticketcinemabe.dto.branch.BranchMinimalDTO;
import org.cybersoft.bookingticketcinemabe.entity.BranchEntity;
import org.cybersoft.bookingticketcinemabe.mapper.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BranchMinimalMapper extends EntityMapper<BranchMinimalDTO, BranchEntity> {
}
