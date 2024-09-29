package org.cybersoft.bookingticketcinemabe.service;

import org.cybersoft.bookingticketcinemabe.dto.BranchDTO;
import org.cybersoft.bookingticketcinemabe.dto.PageableDTO;

public interface BranchService {
    PageableDTO<?> getBranches(int pageNo, int pageLimit, String sortBy);

    BranchDTO getBranch(int id);
}
