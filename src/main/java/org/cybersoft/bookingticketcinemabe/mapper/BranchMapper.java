package org.cybersoft.bookingticketcinemabe.mapper;

import org.cybersoft.bookingticketcinemabe.dto.BranchDTO;
import org.cybersoft.bookingticketcinemabe.entity.BranchEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",
        uses = {MovieDetailMapper.class}
)
public interface BranchMapper extends EntityMapper<BranchDTO, BranchEntity> {

//    @Mapping(target = "role", defaultValue = "user")
//    @Mapping(target = "isEmailVerified", defaultValue = "false")
//    @Mapping(target = "isPhoneVerified", defaultValue = "false")
//    UserEntity toEntity(UserCreationRequest request);
//
//    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
//    void update(@MappingTarget UserEntity user, UserUpdateRequest request);

}
