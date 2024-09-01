package org.cybersoft.bookingticketcinemabe.service.impl;

import lombok.RequiredArgsConstructor;
import org.cybersoft.bookingticketcinemabe.dto.UserDTO;
import org.cybersoft.bookingticketcinemabe.mapper.UserMapper;
import org.cybersoft.bookingticketcinemabe.repository.UserRepository;
import org.cybersoft.bookingticketcinemabe.service.UserService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    @Override
    public List<UserDTO> getAllUsers(int pageNo, int pageLimit, String sortBy) {
        Pageable pageable = PageRequest.of(pageNo, pageLimit, Sort.by(sortBy));
        return userRepository.findAll(pageable).stream().map(userMapper::toUserDto).toList();
    }

    @Override
    public UserDTO getUser(int id) {
        return userRepository.findById(id).map(user -> userMapper.toUserDto(user)).orElseThrow(() -> new RuntimeException("Can't find user"));

    }
}
