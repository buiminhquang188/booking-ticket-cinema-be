package org.cybersoft.bookingticketcinemabe.service.impl;

import io.jsonwebtoken.Claims;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.cybersoft.bookingticketcinemabe.dto.PageableDTO;
import org.cybersoft.bookingticketcinemabe.dto.user.UserDTO;
import org.cybersoft.bookingticketcinemabe.entity.UserEntity;
import org.cybersoft.bookingticketcinemabe.entity.UserEntity_;
import org.cybersoft.bookingticketcinemabe.exception.BadRequestException;
import org.cybersoft.bookingticketcinemabe.exception.ExistedException;
import org.cybersoft.bookingticketcinemabe.exception.NotFoundException;
import org.cybersoft.bookingticketcinemabe.mapper.UserMapper;
import org.cybersoft.bookingticketcinemabe.mapper.pagination.PageableMapper;
import org.cybersoft.bookingticketcinemabe.payload.request.user.ProfileUpdateRequest;
import org.cybersoft.bookingticketcinemabe.payload.request.user.UserCreationRequest;
import org.cybersoft.bookingticketcinemabe.payload.request.user.UserCriteria;
import org.cybersoft.bookingticketcinemabe.payload.request.user.UserUpdateRequest;
import org.cybersoft.bookingticketcinemabe.query.CriteriaApiHelper;
import org.cybersoft.bookingticketcinemabe.query.dto.Pageable;
import org.cybersoft.bookingticketcinemabe.query.impl.SelectQueryImpl;
import org.cybersoft.bookingticketcinemabe.repository.UserRepository;
import org.cybersoft.bookingticketcinemabe.service.UserService;
import org.cybersoft.bookingticketcinemabe.utils.JwtHelper;
import org.springframework.http.HttpHeaders;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    private final CriteriaApiHelper criteriaApiHelper;
    private final JwtHelper jwtHelper;

    @Override
    public PageableDTO<?> getUsers(UserCriteria userCriteria) {
        Pageable pageable = Pageable.builder()
                .pageNumber(userCriteria.getPageNo())
                .pageSize(userCriteria.getPageLimit())
                .sortDefinition(Pageable.sort(userCriteria.getSort(), userCriteria.getOrder()))
                .build();
        SelectQueryImpl<UserEntity> user = this.criteriaApiHelper.select(UserEntity.class);

        if (userCriteria.getId() != null) {
            user.equal(UserEntity_.id, userCriteria.getId());
        }
        if (userCriteria.getFirstName() != null) {
            user.like(UserEntity_.firstName, userCriteria.getFirstName());
        }
        if (userCriteria.getLastName() != null) {
            user.like(UserEntity_.lastName, userCriteria.getLastName());
        }
        if (userCriteria.getFullName() != null) {
            user.like(UserEntity_.fullName, userCriteria.getFullName());
        }
        if (userCriteria.getEmail() != null) {
            user.like(UserEntity_.email, userCriteria.getEmail());
        }
        if (userCriteria.getRole() != null) {
            user.equal(UserEntity_.role, userCriteria.getRole());
        }
        if (userCriteria.getPhone() != null) {
            user.like(UserEntity_.phone, userCriteria.getPhone());
        }
        if (userCriteria.getCreatedAtFrom() != null && userCriteria.getCreatedAtTo() != null) {
            user.between(UserEntity_.createdAt.getName(), userCriteria.getCreatedAtFrom(), userCriteria.getCreatedAtTo());
        }

        if (userCriteria.getUpdatedAtFrom() != null && userCriteria.getUpdatedAtTo() != null) {
            user.between(UserEntity_.updatedAt.getName(), userCriteria.getUpdatedAtFrom(), userCriteria.getUpdatedAtTo());
        }

        return new PageableMapper<>().toDTO(user.findAll(pageable)
                .map(userMapper::toDTO));
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
        UserEntity user = userRepository.findUserByEmail(request.email());
        if (user != null) throw new ExistedException("User existed");

        UserDTO dto;
        try {
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
    public UserDTO updateUser(UserUpdateRequest request, Integer id) {
        UserEntity user = this.userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found user"));

        UserDTO dto = new UserDTO();
        if (user != null && request != null) {
            try {
                userMapper.update(user, request);
                if (request.password() != null) {
                    user.setPassword(passwordEncoder.encode(request.password()));
                }
                UserEntity userUpdated = userRepository.save(user);
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

    @Override
    public UserDTO getUser(HttpHeaders headers) {
        UserDTO userDTO = new UserDTO();
        String authHeader = headers.getFirst("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            Claims claims = this.jwtHelper.parseToken(token);

            if (claims != null) {
                String email = claims.getSubject();
                UserEntity user = this.userRepository.findUserByEmail(email);
                if (user == null) throw new NotFoundException("Can't find user with email: " + email);
                userDTO = this.userMapper.toDTO(user);
            }
        }
        return userDTO;
    }

    @Override
    @Transactional
    public UserDTO updateUser(HttpHeaders header, ProfileUpdateRequest request) {
        try {
            String authHeader = header.getFirst("Authorization");

            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                UserEntity user = new UserEntity();
                String token = authHeader.substring(7);
                Claims claims = this.jwtHelper.parseToken(token);

                if (claims != null) {

                    String email = claims.getSubject();
                    user = this.userRepository.findUserByEmail(email);

                    if (user == null) throw new NotFoundException("Can't find user with email: " + email);

                    userMapper.update(user, request);

//                    if (request.password() != null) user.setPassword(passwordEncoder.encode(request.password()));

                    return userMapper.toDTO(this.userRepository.save(user));
                }
            }
            return null;
        } catch (Exception e) {
            throw new BadRequestException("Fail to update profile");
        }
    }

}
