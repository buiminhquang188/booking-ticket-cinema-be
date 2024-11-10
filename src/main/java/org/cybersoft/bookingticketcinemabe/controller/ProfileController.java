package org.cybersoft.bookingticketcinemabe.controller;

import jakarta.validation.Valid;
import org.cybersoft.bookingticketcinemabe.payload.request.user.ProfileUpdateRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/profile/me")
public interface ProfileController {

    @GetMapping
    ResponseEntity<?> getUser(@RequestHeader HttpHeaders header);

    @PutMapping
    ResponseEntity<?> updateUser(@RequestHeader HttpHeaders header, @RequestBody @Valid ProfileUpdateRequest request);

}
