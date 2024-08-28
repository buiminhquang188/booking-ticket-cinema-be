package org.cybersoft.bookingticketcinemabe.controller.impl;

import org.cybersoft.bookingticketcinemabe.controller.UserController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserControllerImpl implements UserController {
    @Override
    public ResponseEntity<?> getAllUsers() {
        return new ResponseEntity<>("Oke", HttpStatus.OK);
    }
}
