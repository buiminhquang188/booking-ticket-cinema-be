package org.cybersoft.bookingticketcinemabe.controller.impl;

import lombok.RequiredArgsConstructor;
import org.cybersoft.bookingticketcinemabe.controller.UserController;
import org.cybersoft.bookingticketcinemabe.dto.UserDTO;
import org.cybersoft.bookingticketcinemabe.payload.request.UserCreationRequest;
import org.cybersoft.bookingticketcinemabe.payload.request.UserUpdateRequest;
import org.cybersoft.bookingticketcinemabe.payload.response.BaseResponse;
import org.cybersoft.bookingticketcinemabe.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {
    private final UserService userService;

    @Override
    public ResponseEntity<?> getUsers(int pageNo, int pageLimit, String sortBy) {
        Page<?> users = userService.getUsers(pageNo, pageLimit, sortBy);
        return ResponseEntity.ok(BaseResponse.builder()
                .statusCode(HttpStatus.OK.value())
                .message(HttpStatus.OK.getReasonPhrase())
                .data(users)
                .build()
        );
    }

    @Override
    public ResponseEntity<?> getUser(int id) {
        UserDTO user = userService.getUser(id);
        return ResponseEntity.ok(
                BaseResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message(HttpStatus.OK.getReasonPhrase())
                        .data(user)
                        .build()
        );
    }

    @Override
    public ResponseEntity<?> createUser(UserCreationRequest request) {
        return new ResponseEntity<>(BaseResponse.builder().data(userService.createUser(request)).build(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updateUser(UserUpdateRequest request) {
        UserDTO user = userService.updateUser(request);
        return ResponseEntity.ok(BaseResponse.builder()
                .statusCode(HttpStatus.OK.value())
                .message(HttpStatus.OK.getReasonPhrase())
                .data(user)
                .build());
    }

    @Override
    public ResponseEntity<?> deleteUser(int id) {
        UserDTO user = userService.deleteUser(id);
        return ResponseEntity.ok(BaseResponse.builder()
                .statusCode(HttpStatus.OK.value())
                .message(HttpStatus.OK.getReasonPhrase())
                .data("Delete user " + user.getFullName() + " success")
                .build());
    }
}
