package org.cybersoft.bookingticketcinemabe.service.impl;

import lombok.RequiredArgsConstructor;
import org.cybersoft.bookingticketcinemabe.dto.DistrictDTO;
import org.cybersoft.bookingticketcinemabe.dto.PageableDTO;
import org.cybersoft.bookingticketcinemabe.entity.ProvinceEntity;
import org.cybersoft.bookingticketcinemabe.exception.BadRequestException;
import org.cybersoft.bookingticketcinemabe.exception.NotFoundException;
import org.cybersoft.bookingticketcinemabe.mapper.DistrictMapper;
import org.cybersoft.bookingticketcinemabe.mapper.PageableMapper;
import org.cybersoft.bookingticketcinemabe.repository.DistrictRepository;
import org.cybersoft.bookingticketcinemabe.repository.ProvinceRepository;
import org.cybersoft.bookingticketcinemabe.service.DistrictService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DistrictServiceImpl implements DistrictService {

    private final DistrictRepository districtRepository;
    private final ProvinceRepository provinceRepository;
    private final DistrictMapper districtMapper;

    @Override
    public PageableDTO<?> getDistricts(int pageNo, int pageLimit, String sortBy, Integer provinceId) {

        if (pageNo < 0 || pageLimit <= 0)
            throw new BadRequestException("Invalid pagination parameters");

        Pageable pageable = PageRequest.of(pageNo, pageLimit, Sort.by(sortBy));
        Page<?> page;
        if (provinceId != null) {
            ProvinceEntity province = provinceRepository.findById(provinceId)
                    .orElseThrow(() -> new NotFoundException("Not Found Province"));
            page = this.districtRepository.findAllByProvinceId(pageable, provinceId).map(districtMapper::toDTO);
        } else {
            page = this.districtRepository.findAll(pageable)
                    .map(districtMapper::toDTO);
        }
        return new PageableMapper<>().toDTO(page);
    }


    @Override
    public DistrictDTO getDistrict(Integer id) {
        return this.districtRepository.findById(id)
                .map(districtMapper::toDTO)
                .orElseThrow(() -> new NotFoundException("Not found district"));

    }

}
