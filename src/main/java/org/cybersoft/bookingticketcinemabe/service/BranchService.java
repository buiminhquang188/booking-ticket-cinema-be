package org.cybersoft.bookingticketcinemabe.service;

import org.cybersoft.bookingticketcinemabe.dto.PageableDTO;
import org.cybersoft.bookingticketcinemabe.dto.branch.BranchDTO;
import org.cybersoft.bookingticketcinemabe.payload.request.branch.BranchCreationRequest;
import org.cybersoft.bookingticketcinemabe.payload.request.branch.BranchUpdateRequest;

public interface BranchService {
    PageableDTO<?> getBranches(int pageNo, int pageLimit, String sortBy);

    BranchDTO getBranch(Integer id);

    BranchDTO createBranch(BranchCreationRequest request);

    BranchDTO updateBranch(Integer id, BranchUpdateRequest request);

    void deleteBranch(Integer id);
}
