package org.cybersoft.bookingticketcinemabe.service.impl;

import lombok.AllArgsConstructor;
import org.cybersoft.bookingticketcinemabe.dto.hall.HallDetailSeatLayoutDTO;
import org.cybersoft.bookingticketcinemabe.dto.screening.ScreeningDetailSeatLayoutDTO;
import org.cybersoft.bookingticketcinemabe.entity.ScreeningSeatEntity;
import org.cybersoft.bookingticketcinemabe.entity.SeatEntity;
import org.cybersoft.bookingticketcinemabe.mapper.HallMapper;
import org.cybersoft.bookingticketcinemabe.mapper.screening.ScreeningMapper;
import org.cybersoft.bookingticketcinemabe.repository.ScreeningSeatRepository;
import org.cybersoft.bookingticketcinemabe.repository.SeatRepository;
import org.cybersoft.bookingticketcinemabe.service.SeatLayoutService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class SeatLayoutServiceImpl implements SeatLayoutService {
    private final SeatRepository seatRepository;

    private final ScreeningSeatRepository screeningSeatRepository;

    private final HallMapper hallMapper;

    private final ScreeningMapper screeningMapper;

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

    @Override
    public List<List<ScreeningDetailSeatLayoutDTO>> getSeatLayoutByScreeningId(Integer screeningId) {
        List<List<ScreeningDetailSeatLayoutDTO>> screeningSeatsLayout = new ArrayList<>();

        List<ScreeningSeatEntity> screeningSeats = this.screeningSeatRepository.findAllByScreeningId(screeningId);

        screeningSeats.forEach(screeningSeat -> {
            ScreeningDetailSeatLayoutDTO seatLayout = this.screeningMapper.toScreeningDetailSeatLayoutDto(screeningSeat);

            int seatRow = seatLayout.getSeatRow()
                                  .charAt(0) - 'A';
            int seatColumn = seatLayout.getSeatColumn() - 1;

            if (seatColumn == 0) {
                screeningSeatsLayout.add(seatRow, new ArrayList<>());
            }
            screeningSeatsLayout.get(seatRow)
                    .add(seatColumn, seatLayout);
        });

        return screeningSeatsLayout;
    }
}
