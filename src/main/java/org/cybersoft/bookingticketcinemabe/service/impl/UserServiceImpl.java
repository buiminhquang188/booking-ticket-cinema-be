package org.cybersoft.bookingticketcinemabe.service.impl;

import org.cybersoft.bookingticketcinemabe.dto.UserDTO;
import org.cybersoft.bookingticketcinemabe.entity.UserEntity;
import org.cybersoft.bookingticketcinemabe.repository.UserRepository;
import org.cybersoft.bookingticketcinemabe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public List<UserDTO> getAllUsers(int pageNo, int pageLimit, String sortBy) {
        Pageable pageable = PageRequest.of(pageNo, pageLimit, Sort.by(sortBy));
        return userRepository.findAll(pageable).stream().map(user -> {
            UserDTO dto = new UserDTO();
            dto.setId(user.getId());
            dto.setAvatar(user.getAvatar());
            dto.setEmail(user.getEmail());
            dto.setFirstName(user.getFirstName());
            dto.setLastName(user.getLastName());
            dto.setFullName(user.getFullName());
            dto.setRole(user.getRole());
            return dto;
        }).toList();
    }
}
