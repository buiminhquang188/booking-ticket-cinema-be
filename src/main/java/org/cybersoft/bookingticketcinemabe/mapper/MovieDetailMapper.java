package org.cybersoft.bookingticketcinemabe.mapper;

import org.cybersoft.bookingticketcinemabe.dto.MovieDetailDTO;
import org.cybersoft.bookingticketcinemabe.entity.MovieEntity;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface MovieDetailMapper extends EntityMapper<MovieDetailDTO, MovieEntity> {

    //    @Mapping(target = "role", defaultValue = "user")
//    @Mapping(target = "isEmailVerified", defaultValue = "false")
//    @Mapping(target = "isPhoneVerified", defaultValue = "false")
//    UserEntity toEntity(UserCreationRequest request);
//
//    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
//    void update(@MappingTarget UserEntity user, UserUpdateRequest request);
    Set<MovieDetailDTO> toDTOs(Set<MovieEntity> entities);
}
