package org.cybersoft.bookingticketcinemabe.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.cybersoft.bookingticketcinemabe.dto.PageableDTO;
import org.cybersoft.bookingticketcinemabe.dto.UserDTO;
import org.cybersoft.bookingticketcinemabe.entity.UserEntity;
import org.cybersoft.bookingticketcinemabe.exception.BadRequestException;
import org.cybersoft.bookingticketcinemabe.exception.ExistedException;
import org.cybersoft.bookingticketcinemabe.exception.NotFoundException;
import org.cybersoft.bookingticketcinemabe.mapper.PageableMapper;
import org.cybersoft.bookingticketcinemabe.mapper.UserMapper;
import org.cybersoft.bookingticketcinemabe.payload.request.UserCreationRequest;
import org.cybersoft.bookingticketcinemabe.payload.request.UserUpdateRequest;
import org.cybersoft.bookingticketcinemabe.repository.UserRepository;
import org.cybersoft.bookingticketcinemabe.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public PageableDTO<?> getUsers(int pageNo, int pageLimit, String sortBy) {
        Pageable pageable = PageRequest.of(pageNo, pageLimit, Sort.by(sortBy));
        Page<?> page = this.userRepository.findAll(pageable)
                .map(userMapper::toDTO);
        return new PageableMapper<>().toDTO(page);
    }

    @Override
    public UserDTO getUser(int id) {
        return this.userRepository.findById(id)
                .map(userMapper::toDTO)
                .orElseThrow(() -> new NotFoundException("Not found user"));

    }

    @Override
    @Transactional
    public UserDTO createUser(UserCreationRequest request) {
        UserEntity user = userRepository.findUserEntityByEmail(request.email());
        if (user != null) throw new ExistedException("User existed");
        UserDTO dto;
        try {
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            user = userMapper.toEntity(request);
            user.setPassword(passwordEncoder.encode(request.password()));
            UserEntity userCreated = this.userRepository.save(user);
            dto = userMapper.toDTO(userCreated);
        } catch (Exception e) {
            throw new BadRequestException("Fail to create user");
        }

        return dto;
    }

    @Override
    @Transactional
    public UserDTO updateUser(UserUpdateRequest request) {
        UserEntity userUpdate = this.userRepository.findById(request.id())
                .orElseThrow(() -> new NotFoundException("Not found user"));
        UserDTO dto = null;
        if (userUpdate != null) {
            try {
                userMapper.update(userUpdate, request);
                if (request.password() != null) {
                    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                    userUpdate.setPassword(passwordEncoder.encode(request.password()));
                }
                UserEntity userUpdated = userRepository.save(userUpdate);
                dto = userMapper.toDTO(userUpdated);
            } catch (Exception e) {
                throw new BadRequestException("Fail to update user");
            }
        }

        return dto;
    }

    @Override
    @Transactional
    public UserDTO deleteUser(int id) {
        UserEntity userDelete = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found user"));
        UserDTO userDeleteDTO = new UserDTO();
        if (userDelete != null) {
            userDeleteDTO = userMapper.toDTO(userDelete);
            userRepository.delete(userDelete);
        }
        return userDeleteDTO;
    }
}
