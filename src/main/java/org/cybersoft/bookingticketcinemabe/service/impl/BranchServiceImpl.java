package org.cybersoft.bookingticketcinemabe.service.impl;

import lombok.RequiredArgsConstructor;
import org.cybersoft.bookingticketcinemabe.dto.BranchDTO;
import org.cybersoft.bookingticketcinemabe.dto.PageableDTO;
import org.cybersoft.bookingticketcinemabe.exception.NotFoundException;
import org.cybersoft.bookingticketcinemabe.mapper.BranchMapper;
import org.cybersoft.bookingticketcinemabe.mapper.PageableMapper;
import org.cybersoft.bookingticketcinemabe.repository.BranchRepository;
import org.cybersoft.bookingticketcinemabe.service.BranchService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BranchServiceImpl implements BranchService {
    private final BranchRepository branchRepository;
    private final BranchMapper branchMapper;

    @Override
    public PageableDTO<?> getBranches(int pageNo, int pageLimit, String sortBy) {
        Pageable pageable = PageRequest.of(pageNo, pageLimit, Sort.by(sortBy));
        Page<?> page = this.branchRepository.findAll(pageable).map(branchMapper::toDTO);
        return new PageableMapper<>().toDTO(page);
    }

    @Override
    public BranchDTO getBranch(int id) {
        return this.branchRepository.findById(id).map(branchMapper::toDTO)
                .orElseThrow(() -> new NotFoundException("Not found branch"));
    }
}
