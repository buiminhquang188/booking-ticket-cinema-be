package org.cybersoft.bookingticketcinemabe.mapper;

import org.cybersoft.bookingticketcinemabe.dto.UserDTO;
import org.cybersoft.bookingticketcinemabe.entity.UserEntity;
import org.cybersoft.bookingticketcinemabe.payload.response.BaseResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface UserMapper {
    UserDTO toUserDto(UserEntity user);
}
