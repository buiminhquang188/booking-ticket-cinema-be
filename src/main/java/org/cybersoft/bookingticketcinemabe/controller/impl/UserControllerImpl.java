package org.cybersoft.bookingticketcinemabe.controller.impl;

import lombok.RequiredArgsConstructor;
import org.cybersoft.bookingticketcinemabe.controller.UserController;
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
}
