package org.cybersoft.bookingticketcinemabe.service;

import org.cybersoft.bookingticketcinemabe.dto.UserDTO;
import org.cybersoft.bookingticketcinemabe.entity.UserEntity;
import org.cybersoft.bookingticketcinemabe.payload.request.UserCreationRequest;

import java.util.List;

public interface UserService {
    List<UserDTO> getAllUsers(int pageNo, int pageLimit, String sortBy);
    UserDTO getUser(int id);

    boolean createUser (UserCreationRequest request);
}
