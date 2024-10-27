package org.cybersoft.bookingticketcinemabe.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.cybersoft.bookingticketcinemabe.dto.PageableDTO;
import org.cybersoft.bookingticketcinemabe.dto.cinema.CinemaDetailDTO;
import org.cybersoft.bookingticketcinemabe.entity.*;
import org.cybersoft.bookingticketcinemabe.entity.key.IdCinemaProvince;
import org.cybersoft.bookingticketcinemabe.exception.runtime.CinemaNotFoundException;
import org.cybersoft.bookingticketcinemabe.mapper.PageableMapper;
import org.cybersoft.bookingticketcinemabe.mapper.cinema.CinemaMapper;
import org.cybersoft.bookingticketcinemabe.mapper.cinema.CinemaProvinceMapper;
import org.cybersoft.bookingticketcinemabe.payload.request.cinema.CinemaCreationRequest;
import org.cybersoft.bookingticketcinemabe.payload.request.cinema.CinemaCriteria;
import org.cybersoft.bookingticketcinemabe.payload.request.cinema.CinemaUpdateRequest;
import org.cybersoft.bookingticketcinemabe.query.CriteriaApiHelper;
import org.cybersoft.bookingticketcinemabe.query.dto.Pageable;
import org.cybersoft.bookingticketcinemabe.query.impl.SelectQueryImpl;
import org.cybersoft.bookingticketcinemabe.repository.BranchRepository;
import org.cybersoft.bookingticketcinemabe.repository.CinemaProvinceRepository;
import org.cybersoft.bookingticketcinemabe.repository.CinemaRepository;
import org.cybersoft.bookingticketcinemabe.repository.ProvinceRepository;
import org.cybersoft.bookingticketcinemabe.service.CinemaService;
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
    private final CriteriaApiHelper criteriaApiHelper;

    @Override
    public PageableDTO<?> getCinemas(CinemaCriteria cinemaCriteria) {
        Pageable pageable = Pageable.builder()
                .pageNumber(cinemaCriteria.pageNo())
                .pageSize(cinemaCriteria.pageSize())
                .sortDefinition(Pageable.sort(cinemaCriteria.sort(), cinemaCriteria.order()))
                .build();

        SelectQueryImpl<CinemaEntity> cinemas = this.criteriaApiHelper.select(CinemaEntity.class);

        if (cinemaCriteria.name() != null) {
            cinemas.like(CinemaEntity_.name, cinemaCriteria.name());
        }

        if (cinemaCriteria.totalCinemaHall() != null) {
            cinemas.equal(CinemaEntity_.totalCinemaHall, cinemaCriteria.totalCinemaHall());
        }

        if (cinemaCriteria.sort() != null && cinemaCriteria.order() != null) {
            cinemas.order(cinemaCriteria.sort(), cinemaCriteria.order());
        }

        if (cinemaCriteria.createdAtFrom() != null && cinemaCriteria.createdAtTo() != null) {
            cinemas.between(CinemaEntity_.createdAt.getName(), cinemaCriteria.createdAtFrom(), cinemaCriteria.createdAtTo());
        }

        if (cinemaCriteria.updatedAtFrom() != null && cinemaCriteria.updatedAtTo() != null) {
            cinemas.between(CinemaEntity_.updatedAt.getName(), cinemaCriteria.updatedAtFrom(), cinemaCriteria.updatedAtTo());
        }

        return new PageableMapper<>().toDTO(cinemas.findAll(pageable)
                .map(cinemaMapper::toDTO));
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

    @Override
    public CinemaDetailDTO updateCinema(Integer id, CinemaUpdateRequest cinemaUpdateRequest) {
        CinemaEntity cinema = this.cinemaRepository.findById(id)
                .orElseThrow(() -> new CinemaNotFoundException(id));

        cinema.setName(cinemaUpdateRequest.name());
        cinema.setImage(cinemaUpdateRequest.image());

        if (cinemaUpdateRequest.branches() != null && !cinemaUpdateRequest.branches()
                .isEmpty()) {
            List<BranchEntity> branches = this.branchRepository.findAllByIdInOrCinemaId(cinemaUpdateRequest.branches(), id);

            branches.forEach(branch -> {
                if (!cinemaUpdateRequest.branches()
                        .contains(branch.getId())) {
                    branch.setCinema(null);
                    return;
                }
                branch.setCinema(cinema);
            });

            cinema.setBranches(branches);
        }

        CinemaEntity savedCinema = this.cinemaRepository.save(cinema);

        if (cinemaUpdateRequest.provinces() != null && !cinemaUpdateRequest.provinces()
                .isEmpty()) {
            List<CinemaProvinceEntity> existCinemaProvinces = this.cinemaProvinceRepository.findAllByCinemaId(id);

            List<CinemaProvinceEntity> updatedCinemaProvinceEntities = cinemaUpdateRequest.provinces()
                    .stream()
                    .map(provinceId -> this.provinceRepository.findById(provinceId)
                            .orElse(null))
                    .filter(Objects::nonNull)
                    .map(provinceEntity -> this.updateCinemaProvinceEntity(id, provinceEntity.getId(), savedCinema, provinceEntity))
                    .collect(Collectors.toList());

            this.removeExistCinemaProvinceEntity(
                    existCinemaProvinces,
                    cinemaUpdateRequest.provinces(),
                    id
            );
            this.cinemaProvinceRepository.saveAll(updatedCinemaProvinceEntities);
            cinema.setCinemaProvinces(updatedCinemaProvinceEntities);
        }

        return this.cinemaMapper.toCinemaDetailDto(savedCinema);
    }

    @Transactional
    @Override
    public void deleteCinema(Integer id) {
        CinemaEntity cinema = this.cinemaRepository.findById(id)
                .orElseThrow(() -> new CinemaNotFoundException(id));

        List<CinemaProvinceEntity> cinemaProvinceEntities = cinema.getCinemaProvinces();
        List<IdCinemaProvince> idCinemaProvince = cinemaProvinceEntities.stream()
                .map(cinemaProvinceEntity -> cinemaProvinceEntity.getId())
                .collect(Collectors.toList());

        cinema.getBranches()
                .forEach(branch -> {
                    branch.setCinema(null);
                });

        this.cinemaProvinceRepository.deleteAllById(idCinemaProvince);
        this.cinemaRepository.delete(cinema);
    }

    private CinemaProvinceEntity toCinemaProvinceEntity(ProvinceEntity province, CinemaEntity cinema) {
        return this.cinemaProvinceMapper.cinemaAndProvinceToCinemaProvinceEntity(
                cinema,
                province,
                this.cinemaProvinceMapper.toIdCinemaProvince(cinema, province)
        );
    }

    private CinemaProvinceEntity updateCinemaProvinceEntity(Integer cinemaId, Integer provinceId, CinemaEntity cinema, ProvinceEntity provinceEntity) {
        IdCinemaProvince idCinemaProvince = IdCinemaProvince.builder()
                .cinemaId(cinemaId)
                .provinceId(provinceId)
                .build();
        return CinemaProvinceEntity.builder()
                .id(idCinemaProvince)
                .cinema(cinema)
                .province(provinceEntity)
                .build();
    }

    private void removeExistCinemaProvinceEntity(List<CinemaProvinceEntity> existCinemaProvinces, List<Integer> requestProvinceIds, Integer cinemaId) {
        existCinemaProvinces.stream()
                .map(cinemaProvinceEntity -> cinemaProvinceEntity.getProvince()
                        .getId())
                .filter(provinceId -> !requestProvinceIds
                        .contains(provinceId))
                .forEach(provinceId ->
                        this.cinemaProvinceRepository.deleteById(IdCinemaProvince.builder()
                                .cinemaId(cinemaId)
                                .provinceId(provinceId)
                                .build()
                        ));
    }
}
