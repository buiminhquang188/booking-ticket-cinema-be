package org.cybersoft.bookingticketcinemabe.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.cybersoft.bookingticketcinemabe.dto.PageableDTO;
import org.cybersoft.bookingticketcinemabe.dto.screening.ScreeningDTO;
import org.cybersoft.bookingticketcinemabe.dto.screening.ScreeningMinimalDTO;
import org.cybersoft.bookingticketcinemabe.entity.*;
import org.cybersoft.bookingticketcinemabe.exception.BadRequestException;
import org.cybersoft.bookingticketcinemabe.exception.NotFoundException;
import org.cybersoft.bookingticketcinemabe.mapper.MinimalMapper;
import org.cybersoft.bookingticketcinemabe.mapper.PageableMapper;
import org.cybersoft.bookingticketcinemabe.mapper.SeatMapper;
import org.cybersoft.bookingticketcinemabe.mapper.screening.ScreeningMapper;
import org.cybersoft.bookingticketcinemabe.payload.request.ScreeningCreationRequest;
import org.cybersoft.bookingticketcinemabe.payload.request.ScreeningUpdateRequest;
import org.cybersoft.bookingticketcinemabe.repository.*;
import org.cybersoft.bookingticketcinemabe.service.ScreeningService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScreeningServiceImpl implements ScreeningService {
    private final ScreeningRepository screeningRepository;
    private final HallRepository hallRepository;
    private final MovieRepository movieRepository;
    private final ReservationRepository reservationRepository;
    private final SeatReservationRepository seatReservationRepository;
    private final ScreeningSeatRepository screeningSeatRepository;


    private final ScreeningMapper screeningMapper;
    private final MinimalMapper minimalMapper;
    private final SeatMapper seatMapper;

    @Override
    public PageableDTO<?> getScreenings(int pageNo, int pageLimit, String sortBy) {
        Pageable pageable = PageRequest.of(pageNo, pageLimit, Sort.by(sortBy));
        Page<?> page = this.screeningRepository.findAll(pageable).map(screeningMapper::toDTO);
        return new PageableMapper<>().toDTO(page);
    }

    @Override
    public ScreeningDTO getScreening(Integer id) {
        return this.screeningRepository.findById(id).map(screeningMapper::toDTO)
                .orElseThrow(() -> new NotFoundException("Not found screening"));

    }

    @Transactional
    @Override
    public ScreeningMinimalDTO createScreening(ScreeningCreationRequest request) {
        ScreeningEntity screening = screeningMapper.toEntity(request);

        if (request.movieId() != null) {
            screening.setMovie(movieRepository.findById(request.movieId())
                    .orElseThrow(() -> new NotFoundException("Not found movie with id: " + request.movieId())));
            if (screening.getStartTime() != null)
                screening.setEndTime(screening.getStartTime().plusMinutes(screening.getMovie().getTime()));
        }
        if (request.hallId() != null) {
            HallEntity hall = hallRepository.findById(request.hallId())
                    .orElseThrow(() -> new NotFoundException("Not found hall with id: " + request.hallId()));

            if (hall.getSeats() == null && hall.getSeats().isEmpty() && hall.getSeats().contains(null))
                throw new NotFoundException("Not found seats at hall with id: " + hall.getId());

            LocalDateTime startTime = screening.getStartTime();
            LocalDateTime endTime = screening.getEndTime();
            List<ScreeningEntity> overlapTimeScreenings = screeningRepository.findScreeningOverlapTimerInHall(startTime, endTime, hall.getId());
            if (!overlapTimeScreenings.isEmpty()) {
                StringBuilder messageResponse = new StringBuilder("Hall " + hall.getName() + " has overlapping screenings with id(s): ");

                overlapTimeScreenings.forEach(overlapTimeScreening -> {
                    messageResponse.append(overlapTimeScreening.getId())
                            .append(" from ")
                            .append(overlapTimeScreening.getStartTime())
                            .append(" to ")
                            .append(overlapTimeScreening.getEndTime())
                            .append("; ");
                });
                throw new BadRequestException(messageResponse.toString());
            } else {
                try {
                    screening.setHall(hall);
                    screening.setScreeningSeats(new ArrayList<>());
                    screening = screeningRepository.save(screening);
                    final ScreeningEntity savedScreening = screening;
                    List<ScreeningSeatEntity> screeningSeatsCreated = hall.getSeats()
                            .stream()
                            .map(seat -> {
                                ScreeningSeatEntity screeningSeat = seatMapper.toScreeningSeatEntity(seat);
                                screeningSeat.setScreening(savedScreening);
                                return screeningSeat;
                            })
                            .collect(Collectors.toList());
                    screeningSeatsCreated = screeningSeatRepository.saveAll(screeningSeatsCreated);
                    screeningSeatsCreated.forEach(screening::addScreeningSeat);
                } catch (Exception e) {
                    throw new BadRequestException("Something went wrong!!");
                }

            }
        }
        return minimalMapper.toScreeningMinimalDTO(screening);
    }

    @Transactional
    @Override
    public ScreeningDTO updateScreening(Integer id, ScreeningUpdateRequest request) {
        ScreeningEntity screening = screeningRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found screening with id: " + id));
        if (screening != null) {
            screeningMapper.update(screening, request);

            if (request.movieId() != null) {
                screening.setMovie(movieRepository.findById(request.movieId())
                        .orElseThrow(() -> new NotFoundException("Not found movie with id: " + request.movieId())));
                if (screening.getStartTime() != null)
                    screening.setEndTime(screening.getStartTime().plusMinutes(screening.getMovie().getTime()));
            }

            if (request.reservationIds() != null && !request.reservationIds().isEmpty() && screening.getReservations() != null && !request.reservationIds().contains(null)) {

                int reservationSize = Objects.requireNonNull(screening).getReservations().size();
                for (int i = 0; i < reservationSize; i++)
                    screening.removeReservation(screening.getReservations().get(0));

                request.reservationIds().forEach(reservationId -> {
                    ReservationEntity reservationEntity = reservationRepository.findById(reservationId)
                            .orElseThrow(() -> new NotFoundException("Not found reservation with id: " + reservationId));
                    if (reservationEntity != null) screening.addReservation(reservationEntity);
                });
            }
            if (request.seatReservationIds() != null && !request.seatReservationIds().isEmpty() && screening.getSeatReservations() != null && !request.seatReservationIds().contains(null)) {

                int seatReservationSize = Objects.requireNonNull(screening).getSeatReservations().size();
                for (int i = 0; i < seatReservationSize; i++)
                    screening.removeSeatReservation(screening.getSeatReservations().get(0));

                request.seatReservationIds().forEach(seatReservationId -> {
                    SeatReservationEntity seatReservationEntity = seatReservationRepository.findById(seatReservationId)
                            .orElseThrow(() -> new NotFoundException("Not found seat-reservation with id: " + seatReservationId));
                    if (seatReservationEntity != null) screening.addSeatReservation(seatReservationEntity);
                });
            }
        }

        assert screening != null;
        ScreeningEntity screeningUpdated = screeningRepository.save(screening);


        return screeningMapper.toDTO(screeningUpdated);
    }

    @Transactional
    @Override
    public void deleteScreening(Integer id) {
        ScreeningEntity screening = screeningRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found screening with id: " + id));
        if (screening != null) {
            try {
                screeningRepository.delete(screening);
            } catch (Exception e) {
                throw new BadRequestException("Something went wrong!");
            }
        }
    }


}
