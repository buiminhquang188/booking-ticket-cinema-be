package org.cybersoft.bookingticketcinemabe.controller;

import org.cybersoft.bookingticketcinemabe.payload.request.UserCreationRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/users")
public interface UserController {
    @GetMapping
    ResponseEntity<?> getAllUsers(@RequestParam(defaultValue = "0") int pageNo,
                                  @RequestParam(defaultValue = "10") int pageLimit,
                                  @RequestParam(defaultValue = "id") String sortBy);

    @GetMapping("/{id}")
    ResponseEntity<?> getUser(@PathVariable int id);

    @PostMapping
    ResponseEntity<?> createUser (@RequestBody UserCreationRequest request);

}
