package org.cybersoft.bookingticketcinemabe.controller;

import org.cybersoft.bookingticketcinemabe.payload.request.branch.BranchCreationRequest;
import org.cybersoft.bookingticketcinemabe.payload.request.branch.BranchCriteria;
import org.cybersoft.bookingticketcinemabe.payload.request.branch.BranchUpdateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping
public interface BranchController {
    @GetMapping("/branches")
    ResponseEntity<?> getBranches(BranchCriteria branchCriteria);

    @GetMapping("/branch/{id}")
    ResponseEntity<?> getBranch(@PathVariable Integer id);

    @PostMapping("/branch")
    ResponseEntity<?> createBranch(@RequestBody BranchCreationRequest request);

    @PutMapping("/branch/{id}")
    ResponseEntity<?> updateBranch(@PathVariable Integer id, @RequestBody BranchUpdateRequest request);

    @DeleteMapping("/branch/{id}")
    ResponseEntity<?> deleteBranch(@PathVariable Integer id);
}
