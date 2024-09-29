package org.cybersoft.bookingticketcinemabe.mapper;

import org.cybersoft.bookingticketcinemabe.dto.MovieDTO;
import org.cybersoft.bookingticketcinemabe.entity.MovieEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MovieMapper extends EntityMapper<MovieDTO, MovieEntity> {

    //    @Mapping(target = "role", defaultValue = "user")
//    @Mapping(target = "isEmailVerified", defaultValue = "false")
//    @Mapping(target = "isPhoneVerified", defaultValue = "false")
//    UserEntity toEntity(UserCreationRequest request);
//
//    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
//    void update(@MappingTarget UserEntity user, UserUpdateRequest request);

}
