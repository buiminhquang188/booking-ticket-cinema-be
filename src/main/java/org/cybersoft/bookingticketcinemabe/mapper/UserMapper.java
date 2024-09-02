package org.cybersoft.bookingticketcinemabe.mapper;

import org.cybersoft.bookingticketcinemabe.dto.UserDTO;
import org.cybersoft.bookingticketcinemabe.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {
    UserDTO toUserDto(UserEntity user);
}
