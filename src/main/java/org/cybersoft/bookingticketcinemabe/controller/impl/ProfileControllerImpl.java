package org.cybersoft.bookingticketcinemabe.controller.impl;

import lombok.RequiredArgsConstructor;
import org.cybersoft.bookingticketcinemabe.controller.ProfileController;
import org.cybersoft.bookingticketcinemabe.dto.user.UserDTO;
import org.cybersoft.bookingticketcinemabe.payload.request.user.ProfileUpdateRequest;
import org.cybersoft.bookingticketcinemabe.payload.response.BaseResponse;
import org.cybersoft.bookingticketcinemabe.service.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProfileControllerImpl implements ProfileController {
    private final UserService userService;


    @Override
    public ResponseEntity<?> getUser(HttpHeaders headers) {
        UserDTO user = userService.getUser(headers);
        return ResponseEntity.ok(
                BaseResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message(HttpStatus.OK.getReasonPhrase())
                        .data(user)
                        .build()
        );
    }

    @Override
    public ResponseEntity<?> updateUser(HttpHeaders header, ProfileUpdateRequest request) {
        UserDTO user = userService.updateUser(header, request);
        return ResponseEntity.ok(BaseResponse.builder()
                .statusCode(HttpStatus.OK.value())
                .message(HttpStatus.OK.getReasonPhrase())
                .data(user)
                .build());
    }
}

