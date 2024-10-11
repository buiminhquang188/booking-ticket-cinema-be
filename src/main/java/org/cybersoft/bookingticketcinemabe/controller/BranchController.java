package org.cybersoft.bookingticketcinemabe.controller;

import org.cybersoft.bookingticketcinemabe.payload.request.BranchCreationRequest;
import org.cybersoft.bookingticketcinemabe.payload.request.BranchUpdateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping
public interface BranchController {
    @GetMapping("/branches")
    ResponseEntity<?> getBranches(@RequestParam(defaultValue = "0") int pageNo,
                                  @RequestParam(defaultValue = "10") int pageLimit,
                                  @RequestParam(defaultValue = "id") String sortBy);

    @GetMapping("/branch/{id}")
    ResponseEntity<?> getBranch(@PathVariable int id);

    @PostMapping("/branch")
    ResponseEntity<?> createBranch(@RequestBody BranchCreationRequest request);

    @PutMapping("/branch/{id}")
    ResponseEntity<?> updateBranch(@PathVariable int id, @RequestBody BranchUpdateRequest request);
}
