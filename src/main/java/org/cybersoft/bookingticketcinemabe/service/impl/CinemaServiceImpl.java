package org.cybersoft.bookingticketcinemabe.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.cybersoft.bookingticketcinemabe.dto.CinemaDetailDTO;
import org.cybersoft.bookingticketcinemabe.entity.BranchEntity;
import org.cybersoft.bookingticketcinemabe.entity.CinemaEntity;
import org.cybersoft.bookingticketcinemabe.entity.CinemaProvinceEntity;
import org.cybersoft.bookingticketcinemabe.entity.ProvinceEntity;
import org.cybersoft.bookingticketcinemabe.exception.runtime.CinemaNotFoundException;
import org.cybersoft.bookingticketcinemabe.mapper.CinemaMapper;
import org.cybersoft.bookingticketcinemabe.mapper.CinemaProvinceMapper;
import org.cybersoft.bookingticketcinemabe.payload.request.CinemaCreationRequest;
import org.cybersoft.bookingticketcinemabe.repository.BranchRepository;
import org.cybersoft.bookingticketcinemabe.repository.CinemaProvinceRepository;
import org.cybersoft.bookingticketcinemabe.repository.CinemaRepository;
import org.cybersoft.bookingticketcinemabe.repository.ProvinceRepository;
import org.cybersoft.bookingticketcinemabe.service.CinemaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CinemaServiceImpl implements CinemaService {
    private final CinemaRepository cinemaRepository;
    private final BranchRepository branchRepository;
    private final ProvinceRepository provinceRepository;
    private final CinemaProvinceRepository cinemaProvinceRepository;
    private final CinemaMapper cinemaMapper;
    private final CinemaProvinceMapper cinemaProvinceMapper;

    @Override
    public Page<?> getCinemas(int pageNo, int pageSize, String name) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);

        return this.cinemaRepository.findAll(pageable)
                .map(cinemaMapper::toCinemaDTO);
    }

    @Override
    public CinemaDetailDTO getCinema(int id) {
        Optional<CinemaEntity> cinema = this.cinemaRepository.findById(id);
        return cinema.map(cinemaMapper::toCinemaDetailDto)
                .orElseThrow(() -> new CinemaNotFoundException(id));
    }

    @Transactional
    @Override
    public CinemaDetailDTO createCinema(CinemaCreationRequest cinemaCreationRequest) {
        CinemaEntity cinema = CinemaEntity.builder()
                .name(cinemaCreationRequest.name())
                .image(cinemaCreationRequest.image())
                .build();

        if (cinemaCreationRequest.branches() != null && !cinemaCreationRequest.branches()
                .isEmpty()) {
            List<BranchEntity> branchEntities = cinemaCreationRequest.branches()
                    .stream()
                    .map(branchId -> this.branchRepository.findById(branchId)
                            .orElse(null)
                    )
                    .filter(Objects::nonNull)
                    .peek(branchEntity -> branchEntity.setCinema(cinema))
                    .collect(Collectors.toList());
            cinema.setBranches(branchEntities);
        }

        CinemaEntity savedCinema = this.cinemaRepository.save(cinema);

        if (cinemaCreationRequest.provinces() != null && !cinemaCreationRequest.provinces()
                .isEmpty()) {
            List<CinemaProvinceEntity> cinemaProvinceEntities = cinemaCreationRequest.provinces()
                    .stream()
                    .map(provinceId -> this.provinceRepository.findById(provinceId)
                            .orElse(null))
                    .filter(Objects::nonNull)
                    .map(province -> this.toCinemaProvinceEntity(province, cinema))
                    .collect(Collectors.toList());

            cinema.setCinemaProvinces(cinemaProvinceEntities);
            this.cinemaProvinceRepository.saveAll(cinemaProvinceEntities);
        }

        return this.cinemaMapper.toCinemaDetailDto(savedCinema);
    }

    private CinemaProvinceEntity toCinemaProvinceEntity(ProvinceEntity province, CinemaEntity cinema) {
        return this.cinemaProvinceMapper.cinemaAndProvinceToCinemaProvinceEntity(
                cinema,
                province,
                this.cinemaProvinceMapper.toIdCinemaProvince(cinema, province)
        );
    }
}
