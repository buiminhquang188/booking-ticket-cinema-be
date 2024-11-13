package org.cybersoft.bookingticketcinemabe.controller.impl;

import lombok.RequiredArgsConstructor;
import org.cybersoft.bookingticketcinemabe.controller.UserController;
import org.cybersoft.bookingticketcinemabe.dto.PageableDTO;
import org.cybersoft.bookingticketcinemabe.dto.user.UserDTO;
import org.cybersoft.bookingticketcinemabe.payload.request.user.UserCreationRequest;
import org.cybersoft.bookingticketcinemabe.payload.request.user.UserCriteria;
import org.cybersoft.bookingticketcinemabe.payload.request.user.UserUpdateRequest;
import org.cybersoft.bookingticketcinemabe.payload.response.BaseResponse;
import org.cybersoft.bookingticketcinemabe.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {
    private final UserService userService;

    @Override
    public ResponseEntity<?> getUsers(UserCriteria userCriteria) {
        PageableDTO<?> users = userService.getUsers(userCriteria);

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
    public ResponseEntity<?> updateUser(UserUpdateRequest request, Integer id) {
        UserDTO user = userService.updateUser(request, id);
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
