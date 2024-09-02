package org.cybersoft.bookingticketcinemabe.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RequestMapping("/users")
public interface UserController {
    @GetMapping
    ResponseEntity<?> getAllUsers(@RequestParam(defaultValue = "0") int pageNo,
                                  @RequestParam(defaultValue = "10") int pageLimit,
                                  @RequestParam(defaultValue = "id") String sortBy);

    @GetMapping("/{id}")
    ResponseEntity<?> getUser(@PathVariable int id);

}
