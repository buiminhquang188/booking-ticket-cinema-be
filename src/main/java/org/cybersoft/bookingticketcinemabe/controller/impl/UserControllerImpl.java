package org.cybersoft.bookingticketcinemabe.controller.impl;

import org.cybersoft.bookingticketcinemabe.controller.UserController;
import org.cybersoft.bookingticketcinemabe.payload.response.BaseResponse;
import org.cybersoft.bookingticketcinemabe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserControllerImpl implements UserController {
    @Autowired
    private UserService userService;
    @Override
    public ResponseEntity<?> getAllUsers(int pageNo, int pageLimit, String sortBy) {
        BaseResponse response = new BaseResponse<>();
        response.setData(userService.getAllUsers(pageNo, pageLimit, sortBy));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
