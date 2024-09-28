package org.cybersoft.bookingticketcinemabe.service;

import org.cybersoft.bookingticketcinemabe.dto.PageableDTO;
import org.cybersoft.bookingticketcinemabe.dto.UserDTO;
import org.cybersoft.bookingticketcinemabe.payload.request.UserCreationRequest;
import org.cybersoft.bookingticketcinemabe.payload.request.UserUpdateRequest;

public interface UserService {
    PageableDTO<?> getUsers(int pageNo, int pageLimit, String sortBy);

    UserDTO getUser(int id);

    UserDTO createUser(UserCreationRequest request);

    UserDTO updateUser(UserUpdateRequest request);

    UserDTO deleteUser(int id);


}
