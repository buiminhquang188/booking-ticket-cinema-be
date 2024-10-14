package org.cybersoft.bookingticketcinemabe.service.impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.cybersoft.bookingticketcinemabe.dto.HallDetailDTO;
import org.cybersoft.bookingticketcinemabe.dto.PageableDTO;
import org.cybersoft.bookingticketcinemabe.entity.BranchEntity;
import org.cybersoft.bookingticketcinemabe.entity.HallEntity;
import org.cybersoft.bookingticketcinemabe.entity.HallEntity_;
import org.cybersoft.bookingticketcinemabe.exception.NotFoundException;
import org.cybersoft.bookingticketcinemabe.mapper.HallMapper;
import org.cybersoft.bookingticketcinemabe.mapper.PageableMapper;
import org.cybersoft.bookingticketcinemabe.payload.request.HallCreationRequest;
import org.cybersoft.bookingticketcinemabe.payload.request.HallCriteria;
import org.cybersoft.bookingticketcinemabe.payload.request.HallUpdateRequest;
import org.cybersoft.bookingticketcinemabe.query.CriteriaApiHelper;
import org.cybersoft.bookingticketcinemabe.query.dto.Pageable;
import org.cybersoft.bookingticketcinemabe.query.impl.SelectQueryImpl;
import org.cybersoft.bookingticketcinemabe.repository.BranchRepository;
import org.cybersoft.bookingticketcinemabe.repository.HallRepository;
import org.cybersoft.bookingticketcinemabe.service.HallService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class HallServiceImpl implements HallService {
    private final HallRepository hallRepository;

    private final BranchRepository branchRepository;

    private final CriteriaApiHelper criteriaApiHelper;

    private final HallMapper hallMapper;

    @Override
    public PageableDTO<?> getHalls(HallCriteria hallCriteria) {
        Pageable pageable = Pageable.builder()
                .pageNumber(hallCriteria.getPageNo())
                .pageSize(hallCriteria.getPageSize())
                .sortDefinition(Pageable.sort(hallCriteria.getSort(), hallCriteria.getOrder()))
                .build();

        SelectQueryImpl<HallEntity> halls = this.criteriaApiHelper.select(HallEntity.class);

        if (hallCriteria.getName() != null) {
            halls.like(HallEntity_.name, hallCriteria.getName());
        }

        if (hallCriteria.getTotalSeats() != null) {
            halls.equal(HallEntity_.totalSeats, hallCriteria.getTotalSeats());
        }

        if (hallCriteria.getCreatedAtFrom() != null && hallCriteria.getCreatedAtTo() != null) {
            halls.between(HallEntity_.createdAt.getName(), hallCriteria.getCreatedAtFrom(), hallCriteria.getCreatedAtTo());
        }

        if (hallCriteria.getUpdatedAtFrom() != null && hallCriteria.getUpdatedAtTo() != null) {
            halls.between(HallEntity_.updatedAt.getName(), hallCriteria.getUpdatedAtFrom(), hallCriteria.getUpdatedAtTo());
        }

        return new PageableMapper<>().toDTO(
                halls.findAll(pageable)
                        .map(hallMapper::toDTO)
        );
    }

    @Override
    public HallDetailDTO getHall(Integer id) {
        HallEntity hall = this.hallRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Hall " + id + " not found"));
        return this.hallMapper.toHallDetailDto(hall);
    }

    @Transactional
    @Override
    public HallDetailDTO createHall(HallCreationRequest request) {
        BranchEntity branch = this.branchRepository.findById(request.branchId())
                .orElseThrow(() -> new NotFoundException("Branch " + request.branchId() + " not found"));

        HallEntity hall = HallEntity.builder()
                .name(request.name())
                .totalSeats(request.totalSeats())
                .branch(branch)
                .build();

        // TODO: Create hall's seats

        return this.hallMapper.toHallDetailDto(this.hallRepository.save(hall));
    }

    @Transactional
    @Override
    public HallDetailDTO updateHall(Integer id, HallUpdateRequest request) {
        HallEntity hall = this.hallRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Hall " + id + " not found"));

        BranchEntity branch = this.branchRepository.findById(request.branchId())
                .orElseThrow(() -> new NotFoundException("Branch " + request.branchId() + " not found"));

        hall.setName(request.name());
        hall.setTotalSeats(request.totalSeats());
        hall.setBranch(branch);

        // TODO: Update hall's seats

        HallEntity savedHall = this.hallRepository.save(hall);

        return this.hallMapper.toHallDetailDto(savedHall);
    }

    @Transactional
    @Override
    public void deleteHall(Integer id) {
        HallEntity hall = this.hallRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Hall " + id + " not found"));

        // TODO: Delete hall's seats

        this.hallRepository.delete(hall);
    }
}
