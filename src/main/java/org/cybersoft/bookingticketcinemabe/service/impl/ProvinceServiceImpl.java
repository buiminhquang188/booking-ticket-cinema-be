package org.cybersoft.bookingticketcinemabe.service.impl;

import lombok.RequiredArgsConstructor;
import org.cybersoft.bookingticketcinemabe.dto.PageableDTO;
import org.cybersoft.bookingticketcinemabe.dto.ProvinceDTO;
import org.cybersoft.bookingticketcinemabe.exception.NotFoundException;
import org.cybersoft.bookingticketcinemabe.mapper.PageableMapper;
import org.cybersoft.bookingticketcinemabe.mapper.ProvinceMapper;
import org.cybersoft.bookingticketcinemabe.repository.ProvinceRepository;
import org.cybersoft.bookingticketcinemabe.service.ProvinceService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProvinceServiceImpl implements ProvinceService {

    private final ProvinceRepository provinceRepository;
    private final ProvinceMapper provinceMapper;

    @Override
    public PageableDTO<?> getProvinces(int pageNo, int pageLimit, String sortBy) {
        Pageable pageable = PageRequest.of(pageNo, pageLimit, Sort.by(sortBy));
        Page<?> page = this.provinceRepository.findAll(pageable)
                .map(provinceMapper::toDTO);
        return new PageableMapper<>().toDTO(page);
    }

    @Override
    public ProvinceDTO getProvince(Integer id) {
        return this.provinceRepository.findById(id)
                .map(provinceMapper::toDTO)
                .orElseThrow(() -> new NotFoundException("Not found province"));

    }

}
