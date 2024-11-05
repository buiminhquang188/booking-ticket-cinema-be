package org.cybersoft.bookingticketcinemabe.service.impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.cybersoft.bookingticketcinemabe.dto.PageableDTO;
import org.cybersoft.bookingticketcinemabe.dto.hall.HallDetailDTO;
import org.cybersoft.bookingticketcinemabe.dto.hall.HallDetailSeatDTO;
import org.cybersoft.bookingticketcinemabe.dto.hall.HallDetailSeatLayoutDTO;
import org.cybersoft.bookingticketcinemabe.entity.BranchEntity;
import org.cybersoft.bookingticketcinemabe.entity.HallEntity;
import org.cybersoft.bookingticketcinemabe.entity.HallEntity_;
import org.cybersoft.bookingticketcinemabe.entity.SeatEntity;
import org.cybersoft.bookingticketcinemabe.exception.NotFoundException;
import org.cybersoft.bookingticketcinemabe.mapper.HallMapper;
import org.cybersoft.bookingticketcinemabe.mapper.pagination.PageableMapper;
import org.cybersoft.bookingticketcinemabe.payload.request.hall.*;
import org.cybersoft.bookingticketcinemabe.query.CriteriaApiHelper;
import org.cybersoft.bookingticketcinemabe.query.dto.Pageable;
import org.cybersoft.bookingticketcinemabe.query.impl.SelectQueryImpl;
import org.cybersoft.bookingticketcinemabe.repository.BranchRepository;
import org.cybersoft.bookingticketcinemabe.repository.HallRepository;
import org.cybersoft.bookingticketcinemabe.repository.SeatRepository;
import org.cybersoft.bookingticketcinemabe.service.HallService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class HallServiceImpl implements HallService {
    private final HallRepository hallRepository;

    private final BranchRepository branchRepository;

    private final SeatRepository seatRepository;

    private final CriteriaApiHelper criteriaApiHelper;

    private final HallMapper hallMapper;

    @Override
    public PageableDTO<?> getHalls(HallCriteria hallCriteria) {
        Pageable pageable = Pageable.builder()
                .pageNumber(hallCriteria.getPageNo())
                .pageSize(hallCriteria.getPageLimit())
                .sortDefinition(Pageable.sort(hallCriteria.getSort(), hallCriteria.getOrder()))
                .build();

        SelectQueryImpl<HallEntity> halls = this.criteriaApiHelper.select(HallEntity.class);

        if (hallCriteria.getId() != null) {
            halls.equal(HallEntity_.id, hallCriteria.getId());
        }

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
    public HallDetailSeatDTO getHall(Integer id) {
        HallEntity hall = this.hallRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Hall " + id + " not found"));
        HallDetailSeatDTO hallDetailSeatDTO = this.hallMapper.toHallDetailSeatDTO(hall);

        // TODO: Enhance using mapper
        hallDetailSeatDTO.setSeats(this.getSeatsLayout(id));

        return hallDetailSeatDTO;
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

        if (!request.seats()
                .isEmpty()) {
            List<SeatEntity> seats = this.createSeats(hall, request.seats());
            hall.setTotalSeats(seats.size());
            hall.setSeats(seats);
            this.seatRepository.saveAll(seats);
        }

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

        if (!request.seats()
                .isEmpty()) {
            List<SeatEntity> seats = this.seatRepository.findAllByHallId(id);
            List<SeatEntity> seatEntities = this.updateSeats(request.seats(), seats, hall);
            this.seatRepository.saveAll(seatEntities);
        }

        HallEntity savedHall = this.hallRepository.save(hall);

        return this.hallMapper.toHallDetailDto(savedHall);
    }

    @Transactional
    @Override
    public void deleteHall(Integer id) {
        HallEntity hall = this.hallRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Hall " + id + " not found"));

        List<SeatEntity> seats = this.seatRepository.findAllByHallId(id);
        this.seatRepository.deleteAllInBatch(seats);

        this.hallRepository.delete(hall);
    }

    @Override
    public List<List<HallDetailSeatLayoutDTO>> getSeatsLayout(Integer id) {
        List<List<HallDetailSeatLayoutDTO>> seatsLayout = new ArrayList<>();

        List<SeatEntity> seats = this.seatRepository.findAllByHallId(id);
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

    private List<SeatEntity> createSeats(HallEntity hall, List<HallCreateSeat> seats) {
        List<SeatEntity> seatEntities = seats
                .stream()
                .map((seat) -> this.createSeat(hall, seat))
                .collect(Collectors.toList());

        return this.seatRepository.saveAll(seatEntities);
    }

    private SeatEntity createSeat(HallEntity hall, HallCreateSeat seat) {
        StringBuilder stringBuilder = new StringBuilder();
        return SeatEntity.builder()
                .hall(hall)
                .seatRow(seat.seatRow())
                .seatColumn(seat.seatColumn())
                .seatNumber(seat.seatNumber())
                .isActive(seat.isActive())
                .seatCode(
                        stringBuilder.append(seat.seatRow())
                                .append(seat.seatNumber())
                                .toString()
                )
                .build();
    }

    private List<SeatEntity> updateSeats(List<HallUpdateSeat> requestSeats, List<SeatEntity> seats, HallEntity hall) {
        return requestSeats
                .stream()
                .map(requestSeat -> this.enrichSeats(hall, seats, requestSeat))
                .collect(Collectors.toList());
    }

    private boolean isMatch(SeatEntity seat, HallUpdateSeat requestSeat) {
        return seat.getSeatRow()
                       .equals(requestSeat.seatRow()) && seat.getSeatColumn()
                       .equals(requestSeat.seatColumn()) && seat.getSeatNumber()
                       .equals(requestSeat.seatNumber());
    }

    private SeatEntity enrichSeats(HallEntity hall, List<SeatEntity> seats, HallUpdateSeat requestSeat) {
        SeatEntity seatEntity = seats.stream()
                .filter((seat) -> this.isMatch(seat, requestSeat))
                .findFirst()
                .orElseGet(() -> this.updateSeat(hall, requestSeat));
        seatEntity.setIsActive(requestSeat.isActive());
        return seatEntity;
    }

    private SeatEntity updateSeat(HallEntity hall, HallUpdateSeat seat) {
        StringBuilder stringBuilder = new StringBuilder();
        return SeatEntity.builder()
                .hall(hall)
                .seatRow(seat.seatRow())
                .seatColumn(seat.seatColumn())
                .seatNumber(seat.seatNumber())
                .isActive(seat.isActive())
                .seatCode(
                        stringBuilder.append(seat.seatRow())
                                .append(seat.seatNumber())
                                .toString()
                )
                .build();
    }
}
