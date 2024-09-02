package org.cybersoft.bookingticketcinemabe.controller.impl;

import lombok.RequiredArgsConstructor;
import org.cybersoft.bookingticketcinemabe.controller.UserController;
import org.cybersoft.bookingticketcinemabe.payload.request.UserCreationRequest;
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
    public ResponseEntity<?> getAllUsers(int pageNo, int pageLimit, String sortBy) {
        BaseResponse response = BaseResponse.builder().data(userService.getAllUsers(pageNo, pageLimit, sortBy)).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getUser(int id) {
        return new ResponseEntity<>(BaseResponse.builder().data(userService.getUser(id)).build(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> createUser(UserCreationRequest request) {
        return new ResponseEntity<>(BaseResponse.builder().data(userService.createUser(request)).build(), HttpStatus.OK );
    }
}
