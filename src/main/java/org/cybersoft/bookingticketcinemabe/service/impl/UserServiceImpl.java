package org.cybersoft.bookingticketcinemabe.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.cybersoft.bookingticketcinemabe.dto.UserDTO;
import org.cybersoft.bookingticketcinemabe.entity.UserEntity;
import org.cybersoft.bookingticketcinemabe.exception.UserException;
import org.cybersoft.bookingticketcinemabe.mapper.UserMapper;
import org.cybersoft.bookingticketcinemabe.payload.request.UserCreationRequest;
import org.cybersoft.bookingticketcinemabe.payload.request.UserUpdateRequest;
import org.cybersoft.bookingticketcinemabe.repository.UserRepository;
import org.cybersoft.bookingticketcinemabe.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public Page<?> getUsers(int pageNo, int pageLimit, String sortBy) {
        Pageable pageable = PageRequest.of(pageNo, pageLimit, Sort.by(sortBy));
        return this.userRepository.findAll(pageable).map(userMapper::toDTO);
    }

    @Override
    public UserDTO getUser(int id) {
        return this.userRepository.findById(id).map(userMapper::toDTO)
                .orElseThrow(() -> new UserException("Can't find user"));

    }

    @Override
    @Transactional
    public UserDTO createUser(UserCreationRequest request) {
        UserEntity user = userRepository.findUserEntityByEmail(request.email());
        if (user != null) throw new UserException("Email existed");
        UserDTO dto;
        try {
            UserEntity userCreated = this.userRepository.save(userMapper.toEntity(request));
            dto = userMapper.toDTO(userCreated);
        } catch (Exception e) {
            throw new UserException("Fail to create user");
        }

        return dto;
    }

    @Override
    @Transactional
    public UserDTO updateUser(UserUpdateRequest request) {
        UserEntity userUpdate = this.userRepository.findById(request.id())
                .orElseThrow(() -> new UserException("Can't find user"));
        UserDTO dto = null;
        if (userUpdate != null) {
            try {
                userMapper.update(userUpdate, request);
                UserEntity userUpdated = userRepository.save(userUpdate);
                dto = userMapper.toDTO(userUpdated);
            } catch (Exception e) {
                throw new UserException("Fail to update user");
            }
        }

        return dto;
    }

    @Override
    @Transactional
    public UserDTO deleteUser(int id) {
        UserEntity userDelete = userRepository.findById(id).orElseThrow(() -> new UserException("Can't find user"));
        UserDTO userDeleteDTO = new UserDTO();
        if (userDelete != null) {
            userDeleteDTO = userMapper.toDTO(userDelete);
            userRepository.delete(userDelete);
        }
        return userDeleteDTO;
    }
}
