package org.cybersoft.bookingticketcinemabe.service;

import org.cybersoft.bookingticketcinemabe.dto.UserDTO;

import java.util.List;

public interface UserService {
    List<UserDTO> getAllUsers(int pageNo, int pageLimit, String sortBy);
}
