package org.cybersoft.bookingticketcinemabe.controller;

import jakarta.validation.Valid;
import org.cybersoft.bookingticketcinemabe.payload.request.user.UserCreationRequest;
import org.cybersoft.bookingticketcinemabe.payload.request.user.UserCriteria;
import org.cybersoft.bookingticketcinemabe.payload.request.user.UserUpdateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@PreAuthorize("hasAuthority('ID_' + #id) or hasRole('ADMIN') or hasRole('SUPER_ADMIN')")
@RequestMapping()
public interface UserController {
    @GetMapping("/users")
    ResponseEntity<?> getUsers(UserCriteria userCriteria);

    @GetMapping("/user/{id}")
    ResponseEntity<?> getUser(@PathVariable int id);

    @PostMapping("/user")
    ResponseEntity<?> createUser(@RequestBody @Valid UserCreationRequest request);

    @PutMapping("/user/{id}")
    ResponseEntity<?> updateUser(@RequestBody @Valid UserUpdateRequest request, @PathVariable Integer id);

    @DeleteMapping("/user/{id}")
    ResponseEntity<?> deleteUser(@PathVariable int id);

}
