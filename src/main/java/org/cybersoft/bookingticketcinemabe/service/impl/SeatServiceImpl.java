package org.cybersoft.bookingticketcinemabe.service.impl;

import lombok.AllArgsConstructor;
import org.cybersoft.bookingticketcinemabe.dto.PageableDTO;
import org.cybersoft.bookingticketcinemabe.dto.SeatDetailDTO;
import org.cybersoft.bookingticketcinemabe.entity.SeatEntity;
import org.cybersoft.bookingticketcinemabe.entity.SeatEntity_;
import org.cybersoft.bookingticketcinemabe.exception.runtime.NotFoundException;
import org.cybersoft.bookingticketcinemabe.mapper.PageableMapper;
import org.cybersoft.bookingticketcinemabe.mapper.SeatMapper;
import org.cybersoft.bookingticketcinemabe.payload.request.seat.SeatCriteria;
import org.cybersoft.bookingticketcinemabe.query.CriteriaApiHelper;
import org.cybersoft.bookingticketcinemabe.query.dto.Pageable;
import org.cybersoft.bookingticketcinemabe.query.impl.SelectQueryImpl;
import org.cybersoft.bookingticketcinemabe.repository.SeatRepository;
import org.cybersoft.bookingticketcinemabe.service.SeatService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SeatServiceImpl implements SeatService {
    private final SeatRepository seatRepository;

    private final CriteriaApiHelper criteriaApiHelper;

    private final SeatMapper seatMapper;

    @Override
    public PageableDTO<List<SeatDetailDTO>> getSeats(SeatCriteria seatCriteria) {
        Pageable pageable = Pageable.builder()
                .pageNumber(seatCriteria.getPageNo())
                .pageSize(seatCriteria.getPageLimit())
                .sortDefinition(Pageable.sort(seatCriteria.getSort(), seatCriteria.getOrder()))
                .build();

        SelectQueryImpl<SeatEntity> seat = this.criteriaApiHelper.select(SeatEntity.class);

        seat.equal(SeatEntity_.isActive, true);

        if (seatCriteria.getSeatRow() != null) {
            seat.equal(SeatEntity_.seatRow, seatCriteria.getSeatRow());
        }

        if (seatCriteria.getSeatColumn() != null) {
            seat.equal(SeatEntity_.seatColumn, seatCriteria.getSeatColumn());
        }

        if (seatCriteria.getCreatedAtFrom() != null && seatCriteria.getCreatedAtTo() != null) {
            seat.between(SeatEntity_.createdAt.getName(), seatCriteria.getCreatedAtFrom(), seatCriteria.getCreatedAtTo());
        }

        if (seatCriteria.getUpdatedAtFrom() != null & seatCriteria.getUpdatedAtTo() != null) {
            seat.between(SeatEntity_.updatedAt.getName(), seatCriteria.getUpdatedAtFrom(), seatCriteria.getUpdatedAtTo());
        }

        return new PageableMapper<>().toDTO(
                seat.findAll(pageable)
                        .map(seatMapper::toSeatDetailDTO)
        );
    }

    @Override
    public SeatDetailDTO getSeat(Integer id) {
        SeatEntity seat = this.seatRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Seat not found!"));

        return this.seatMapper.toSeatDetailDTO(seat);
    }
}
