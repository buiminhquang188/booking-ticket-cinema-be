package org.cybersoft.bookingticketcinemabe.service.impl;

import lombok.AllArgsConstructor;
import org.cybersoft.bookingticketcinemabe.dto.hall.HallDetailSeatLayoutDTO;
import org.cybersoft.bookingticketcinemabe.entity.SeatEntity;
import org.cybersoft.bookingticketcinemabe.mapper.HallMapper;
import org.cybersoft.bookingticketcinemabe.repository.SeatRepository;
import org.cybersoft.bookingticketcinemabe.service.SeatLayoutService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class SeatLayoutServiceImpl implements SeatLayoutService {
    private final SeatRepository seatRepository;

    private final HallMapper hallMapper;

    @Override
    public List<List<HallDetailSeatLayoutDTO>> getSeatLayoutByHallId(Integer hallId) {
        List<List<HallDetailSeatLayoutDTO>> seatsLayout = new ArrayList<>();

        List<SeatEntity> seats = this.seatRepository.findAllByHallId(hallId);
        seats.forEach(seat -> {
            HallDetailSeatLayoutDTO seatLayout = this.hallMapper.toHallDetailSeatLayoutDto(seat);

            int seatRow = seatLayout.getSeatRow()
                                  .charAt(0) - 'A';
            int seatColumn = seatLayout.getSeatColumn() - 1;

            if (seatColumn == 0) {
                seatsLayout.add(seatRow, new ArrayList<>());
            }
            seatsLayout.get(seatRow)
                    .add(seatColumn, seatLayout);
        });

        return seatsLayout;
    }
}
