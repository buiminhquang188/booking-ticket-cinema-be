package org.cybersoft.bookingticketcinemabe.mapper;

import org.cybersoft.bookingticketcinemabe.dto.user.UserDTO;
import org.cybersoft.bookingticketcinemabe.entity.UserEntity;
import org.cybersoft.bookingticketcinemabe.payload.request.user.ProfileUpdateRequest;
import org.cybersoft.bookingticketcinemabe.payload.request.user.UserCreationRequest;
import org.cybersoft.bookingticketcinemabe.payload.request.user.UserUpdateRequest;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring",
        uses = {ReservationMapper.class})
public interface UserMapper extends EntityMapper<UserDTO, UserEntity> {

    @Mapping(target = "isEmailVerified", defaultValue = "false")
    @Mapping(target = "isPhoneVerified", defaultValue = "false")
    @Mapping(target = "role", defaultValue = "user")
    UserEntity toEntity(UserCreationRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(@MappingTarget UserEntity user, UserUpdateRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(@MappingTarget UserEntity user, ProfileUpdateRequest request);


    default String mapRoles(String role, List<String> roles) {

        String joinedRoles;

        if (role != null) {
            joinedRoles = role;
        } else if (roles != null && !roles.isEmpty()) {
            joinedRoles = "";
            joinedRoles += String.join(",", roles);
        } else joinedRoles = "user";

        return joinedRoles;
    }
}
