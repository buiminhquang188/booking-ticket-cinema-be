package org.cybersoft.bookingticketcinemabe.service.impl;

import lombok.RequiredArgsConstructor;
import org.cybersoft.bookingticketcinemabe.dto.user.UserDetailsCustom;
import org.cybersoft.bookingticketcinemabe.entity.UserEntity;
import org.cybersoft.bookingticketcinemabe.exception.runtime.AuthenticateException;
import org.cybersoft.bookingticketcinemabe.payload.request.authentication.AuthenticateRequest;
import org.cybersoft.bookingticketcinemabe.repository.UserRepository;
import org.cybersoft.bookingticketcinemabe.service.AuthenticationService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetailsCustom checkLogin(AuthenticateRequest request) {
        UserEntity user = this.userRepository.findUserByEmail(request.email());

        if (user == null || !passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new AuthenticateException("Email or password is not valid");
        }

        return new UserDetailsCustom(user);
    }
}
