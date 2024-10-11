package org.cybersoft.bookingticketcinemabe.service;

import org.cybersoft.bookingticketcinemabe.dto.BranchDTO;
import org.cybersoft.bookingticketcinemabe.dto.PageableDTO;
import org.cybersoft.bookingticketcinemabe.payload.request.BranchCreationRequest;
import org.cybersoft.bookingticketcinemabe.payload.request.BranchUpdateRequest;

public interface BranchService {
    PageableDTO<?> getBranches(int pageNo, int pageLimit, String sortBy);

    BranchDTO getBranch(int id);

    BranchDTO createBranch(BranchCreationRequest request);

    BranchDTO updateBranch(int id, BranchUpdateRequest request);
}
