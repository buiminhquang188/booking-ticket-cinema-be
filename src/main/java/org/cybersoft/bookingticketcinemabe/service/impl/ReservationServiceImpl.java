package org.cybersoft.bookingticketcinemabe.service.impl;

import lombok.AllArgsConstructor;
import org.cybersoft.bookingticketcinemabe.dto.ReservationDTO;
import org.cybersoft.bookingticketcinemabe.entity.ReservationEntity;
import org.cybersoft.bookingticketcinemabe.entity.ScreeningEntity;
import org.cybersoft.bookingticketcinemabe.entity.ScreeningSeatEntity;
import org.cybersoft.bookingticketcinemabe.entity.SeatReservationEntity;
import org.cybersoft.bookingticketcinemabe.enums.ReservationStatus;
import org.cybersoft.bookingticketcinemabe.enums.ScreeningStatus;
import org.cybersoft.bookingticketcinemabe.exception.runtime.NotFoundException;
import org.cybersoft.bookingticketcinemabe.mapper.ReservationMapper;
import org.cybersoft.bookingticketcinemabe.payload.request.reservation.ReservationBookingRequest;
import org.cybersoft.bookingticketcinemabe.repository.ReservationRepository;
import org.cybersoft.bookingticketcinemabe.repository.ScreeningRepository;
import org.cybersoft.bookingticketcinemabe.repository.ScreeningSeatRepository;
import org.cybersoft.bookingticketcinemabe.repository.SeatReservationRepository;
import org.cybersoft.bookingticketcinemabe.service.ReservationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ReservationServiceImpl implements ReservationService {
    private final ScreeningRepository screeningRepository;

    private final ScreeningSeatRepository screeningSeatRepository;

    private final ReservationRepository reservationRepository;

    private final SeatReservationRepository seatReservationRepository;

    private final ReservationMapper reservationMapper;

    @Override
    @Transactional
    public ReservationDTO bookingTicket(Integer screeningId, ReservationBookingRequest reservationBookingRequest) {
        ScreeningEntity screening = this.getScreeningById(screeningId);
        List<ScreeningSeatEntity> seats = this.getSeatsByIds(reservationBookingRequest.seatIds(), screeningId);

        // Check if time reservation is valid
        if (reservationBookingRequest.timeReservation()
                    .isAfter(screening.getStartTime()) && LocalDateTime.now()
                    .isAfter(screening.getStartTime())) {
            throw new NotFoundException("Screening has already started");
        }


        // Check if seat is not found
        List<Integer> notFoundSeatIds = this.getNotFoundSeatIds(reservationBookingRequest.seatIds(), seats);

        if (!notFoundSeatIds.isEmpty()) {
            throw new NotFoundException("Seat " + notFoundSeatIds.stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(",")) + " not found");
        }

        // Check if seat is already reserved
        List<Integer> bookedSeats = this.getBookedSeats(seats);

        if (!bookedSeats.isEmpty()) {
            throw new NotFoundException("Seat +" + bookedSeats.stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(",")) + " is already booked");
        }

        // Update seat booked flag
        seats.forEach(seat -> seat.setIsBooked(true));

        ReservationEntity newReservation = this.createReservation(screening, this.calculateTotalPrice(seats), reservationBookingRequest.timeReservation());
        ReservationEntity savedReservation = this.reservationRepository.save(newReservation);

        List<SeatReservationEntity> seatReservations = seats.stream()
                .map(seat -> this.createSeatReservation(
                        seat.getPrice(),
                        screening,
                        seat,
                        savedReservation
                ))
                .collect(Collectors.toList());
        this.seatReservationRepository.saveAll(seatReservations);

        if (screening.getStatus()
                .equals(ScreeningStatus.NEW.name())) {
            screening.setStatus(ScreeningStatus.BOOKED.name());
        }

        return this.reservationMapper.toDTO(savedReservation);
    }

    @Override
    @Transactional
    public ReservationDTO cancelBooking(Integer screeningId, Integer reservationId) {
        ReservationEntity reservation = this.getReservationById(reservationId);

        if (reservation.getStatus()
                .equals(ReservationStatus.CANCELLED.name())) {
            throw new NotFoundException("Reservation is already cancelled");
        }

        List<SeatReservationEntity> seatReservations = reservation.getSeatReservations();
        List<ScreeningSeatEntity> screeningSeats = seatReservations.stream()
                .map(SeatReservationEntity::getScreeningSeat)
                .peek(seat -> seat.setIsBooked(false))
                .collect(Collectors.toList());
        this.screeningSeatRepository.saveAll(screeningSeats);

        reservation.setStatus(ReservationStatus.CANCELLED.name());

        return this.reservationMapper.toDTO(reservation);
    }

    private ScreeningEntity getScreeningById(Integer screeningId) {
        return screeningRepository.findById(screeningId)
                .orElseThrow(() -> new NotFoundException("Screening not found"));
    }

    private List<ScreeningSeatEntity> getSeatsByIds(List<Integer> seatIds, Integer screeningId) {
        return this.screeningSeatRepository.findAllByIdInAndScreeningId(seatIds, screeningId);
    }

    private List<Integer> getNotFoundSeatIds(List<Integer> seatIds, List<ScreeningSeatEntity> seats) {
        return seatIds
                .stream()
                .filter(seatId -> seats.stream()
                        .noneMatch(seat -> seat.getId()
                                .equals(seatId)))
                .toList();
    }

    private List<Integer> getBookedSeats(List<ScreeningSeatEntity> seats) {
        return seats.stream()
                .filter(ScreeningSeatEntity::getIsBooked)
                .map(ScreeningSeatEntity::getId)
                .toList();
    }

    private Double calculateTotalPrice(List<ScreeningSeatEntity> seats) {
        return seats.stream()
                .map(ScreeningSeatEntity::getPrice)
                .reduce(0.0, Double::sum);
    }

    private ReservationEntity createReservation(ScreeningEntity screening, Double totalPrice, LocalDateTime timeReservation) {
        return ReservationEntity.builder()
                .screening(screening)
                .timeReservation(timeReservation)
                .totalPrice(totalPrice)
                .status(ReservationStatus.BOOKED.name())
                .build();
    }

    private SeatReservationEntity createSeatReservation(Double price, ScreeningEntity screening, ScreeningSeatEntity seat, ReservationEntity savedReservation) {
        return SeatReservationEntity.builder()
                .price(price)
                .screening(screening)
                .screeningSeat(seat)
                .reservation(savedReservation)
                .build();
    }

    private ReservationEntity getReservationById(Integer reservationId) {
        return this.reservationRepository.findById(reservationId)
                .orElseThrow(() -> new NotFoundException("Reservation not found"));
    }
}
