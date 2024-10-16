package org.cybersoft.bookingticketcinemabe.service.impl;

import lombok.AllArgsConstructor;
import org.cybersoft.bookingticketcinemabe.entity.SeatEntity;
import org.cybersoft.bookingticketcinemabe.mapper.PageableMapper;
import org.cybersoft.bookingticketcinemabe.mapper.SeatMapper;
import org.cybersoft.bookingticketcinemabe.payload.request.seat.SeatCriteria;
import org.cybersoft.bookingticketcinemabe.query.CriteriaApiHelper;
import org.cybersoft.bookingticketcinemabe.query.dto.Pageable;
import org.cybersoft.bookingticketcinemabe.query.impl.SelectQueryImpl;
import org.cybersoft.bookingticketcinemabe.repository.SeatRepository;
import org.cybersoft.bookingticketcinemabe.service.SeatService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SeatServiceImpl implements SeatService {
    private final SeatRepository seatRepository;

    private final CriteriaApiHelper criteriaApiHelper;

    private final SeatMapper seatMapper;

    @Override
    public Object getSeats(SeatCriteria seatCriteria) {
        Pageable pageable = Pageable.builder()
                .pageNumber(seatCriteria.getPageNo())
                .pageSize(seatCriteria.getPageSize())
                .sortDefinition(Pageable.sort(seatCriteria.getSort(), seatCriteria.getOrder()))
                .build();

        SelectQueryImpl<SeatEntity> seat = this.criteriaApiHelper.select(SeatEntity.class);

        return new PageableMapper<>().toDTO(
                seat.findAll(pageable)
                        .map(seatMapper::toSeatDetailDTO)
        );
    }

    @Override
    public Object getSeat(Integer id) {
        return null;
    }

    @Override
    public Object createSeat() {
        return null;
    }

    @Override
    public Object updateSeat() {
        return null;
    }

    @Override
    public Object deleteSeat(Integer id) {
        return null;
    }
}
