package org.cybersoft.bookingticketcinemabe.service;

import org.cybersoft.bookingticketcinemabe.dto.PageableDTO;
import org.cybersoft.bookingticketcinemabe.dto.UserDTO;
import org.cybersoft.bookingticketcinemabe.payload.request.user.UserCreationRequest;
import org.cybersoft.bookingticketcinemabe.payload.request.user.UserUpdateRequest;

public interface UserService {
    PageableDTO<?> getUsers(int pageNo, int pageLimit, String sortBy);

    UserDTO getUser(int id);

    UserDTO createUser(UserCreationRequest request);

    UserDTO updateUser(UserUpdateRequest request, Integer id);

    UserDTO deleteUser(int id);


}
