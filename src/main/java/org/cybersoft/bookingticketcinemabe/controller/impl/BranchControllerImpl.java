package org.cybersoft.bookingticketcinemabe.controller.impl;

import lombok.RequiredArgsConstructor;
import org.cybersoft.bookingticketcinemabe.controller.BranchController;
import org.cybersoft.bookingticketcinemabe.dto.PageableDTO;
import org.cybersoft.bookingticketcinemabe.dto.branch.BranchDTO;
import org.cybersoft.bookingticketcinemabe.payload.request.branch.BranchCreationRequest;
import org.cybersoft.bookingticketcinemabe.payload.request.branch.BranchUpdateRequest;
import org.cybersoft.bookingticketcinemabe.payload.response.BaseResponse;
import org.cybersoft.bookingticketcinemabe.service.BranchService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BranchControllerImpl implements BranchController {
    private final BranchService branchService;

    @Override
    public ResponseEntity<?> getBranches(int pageNo, int pageLimit, String sortBy) {
        PageableDTO<?> branches = branchService.getBranches(pageNo, pageLimit, sortBy);
        return ResponseEntity.ok(BaseResponse.builder()
                .statusCode(HttpStatus.OK.value())
                .message(HttpStatus.OK.getReasonPhrase())
                .data(branches)
                .build()
        );
    }

    @Override
    public ResponseEntity<?> getBranch(Integer id) {
        BranchDTO branch = branchService.getBranch(id);
        return ResponseEntity.ok(
                BaseResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message(HttpStatus.OK.getReasonPhrase())
                        .data(branch)
                        .build()
        );

    }

    @Override
    public ResponseEntity<?> createBranch(BranchCreationRequest request) {
        BranchDTO branch = branchService.createBranch(request);
        return ResponseEntity.ok(
                BaseResponse.builder()
                        .statusCode(HttpStatus.CREATED.value())
                        .message(HttpStatus.CREATED.getReasonPhrase())
                        .data(branch)
                        .build()
        );
    }

    @Override
    public ResponseEntity<?> updateBranch(Integer id, BranchUpdateRequest request) {
        BranchDTO branch = branchService.updateBranch(id, request);
        return ResponseEntity.ok(
                BaseResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message(HttpStatus.OK.getReasonPhrase())
                        .data(branch)
                        .build()
        );
    }

    @Override
    public ResponseEntity<?> deleteBranch(Integer id) {
        this.branchService.deleteBranch(id);

        return ResponseEntity.ok(
                BaseResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message(HttpStatus.OK.getReasonPhrase())
                        .data(null)
                        .build()
        );
    }
}
