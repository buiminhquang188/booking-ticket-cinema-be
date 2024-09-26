package org.cybersoft.bookingticketcinemabe.service;

import org.cybersoft.bookingticketcinemabe.dto.UserDTO;
import org.cybersoft.bookingticketcinemabe.payload.request.UserCreationRequest;
import org.cybersoft.bookingticketcinemabe.payload.request.UserUpdateRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {
    Page<?> getUsers(int pageNo, int pageLimit, String sortBy);

    UserDTO getUser(int id);

    UserDTO createUser (UserCreationRequest request);

    UserDTO updateUser (UserUpdateRequest request);

    UserDTO deleteUser (int id);


}
