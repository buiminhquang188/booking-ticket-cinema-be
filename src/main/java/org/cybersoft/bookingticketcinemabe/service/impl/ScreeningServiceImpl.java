package org.cybersoft.bookingticketcinemabe.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.cybersoft.bookingticketcinemabe.dto.PageableDTO;
import org.cybersoft.bookingticketcinemabe.dto.screening.ScreeningDTO;
import org.cybersoft.bookingticketcinemabe.dto.screening.ScreeningDetailDTO;
import org.cybersoft.bookingticketcinemabe.entity.HallEntity;
import org.cybersoft.bookingticketcinemabe.entity.ScreeningEntity;
import org.cybersoft.bookingticketcinemabe.entity.ScreeningEntity_;
import org.cybersoft.bookingticketcinemabe.entity.ScreeningSeatEntity;
import org.cybersoft.bookingticketcinemabe.enums.ScreeningStatus;
import org.cybersoft.bookingticketcinemabe.exception.BadRequestException;
import org.cybersoft.bookingticketcinemabe.exception.NotFoundException;
import org.cybersoft.bookingticketcinemabe.mapper.MinimalMapper;
import org.cybersoft.bookingticketcinemabe.mapper.pagination.PageableMapper;
import org.cybersoft.bookingticketcinemabe.mapper.screening.ScreeningMapper;
import org.cybersoft.bookingticketcinemabe.payload.request.screening.ScreeningCreationRequest;
import org.cybersoft.bookingticketcinemabe.payload.request.screening.ScreeningCriteria;
import org.cybersoft.bookingticketcinemabe.payload.request.screening.ScreeningUpdateRequest;
import org.cybersoft.bookingticketcinemabe.payload.request.screening.ScreeningUpdateSeat;
import org.cybersoft.bookingticketcinemabe.query.CriteriaApiHelper;
import org.cybersoft.bookingticketcinemabe.query.dto.Pageable;
import org.cybersoft.bookingticketcinemabe.query.impl.SelectQueryImpl;
import org.cybersoft.bookingticketcinemabe.repository.HallRepository;
import org.cybersoft.bookingticketcinemabe.repository.MovieRepository;
import org.cybersoft.bookingticketcinemabe.repository.ScreeningRepository;
import org.cybersoft.bookingticketcinemabe.repository.ScreeningSeatRepository;
import org.cybersoft.bookingticketcinemabe.service.ScreeningService;
import org.cybersoft.bookingticketcinemabe.service.SeatLayoutService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScreeningServiceImpl implements ScreeningService {
    private final SeatLayoutService seatLayoutService;
    private final ScreeningRepository screeningRepository;
    private final HallRepository hallRepository;
    private final MovieRepository movieRepository;
    private final ScreeningSeatRepository screeningSeatRepository;
    private final ScreeningMapper screeningMapper;
    private final MinimalMapper minimalMapper;
    private final CriteriaApiHelper criteriaApiHelper;

    @Override
    public PageableDTO<?> getScreenings(ScreeningCriteria screeningCriteria) {
        Pageable pageable = Pageable.builder()
                .pageNumber(screeningCriteria.getPageNo())
                .pageSize(screeningCriteria.getPageLimit())
                .sortDefinition(Pageable.sort(screeningCriteria.getSort(), screeningCriteria.getOrder()))
                .build();
        SelectQueryImpl<ScreeningEntity> screening = this.criteriaApiHelper.select(ScreeningEntity.class);

        if (screeningCriteria.getId() != null) {
            screening.equal(ScreeningEntity_.id, screeningCriteria.getId());
        }
        if (screeningCriteria.getStatus() != null) {
            screening.like(ScreeningEntity_.status, screeningCriteria.getStatus());
        }
        if (screeningCriteria.getStartTimeFrom() != null && screeningCriteria.getStartTimeTo() != null) {
            screening.between(ScreeningEntity_.startTime.getName(), screeningCriteria.getStartTimeFrom(), screeningCriteria.getStartTimeFrom());
        }
        if (screeningCriteria.getEndTimeFrom() != null && screeningCriteria.getEndTimeTo() != null) {
            screening.between(ScreeningEntity_.endTime.getName(), screeningCriteria.getEndTimeFrom(), screeningCriteria.getEndTimeTo());
        }
        if (screeningCriteria.getCreatedAtFrom() != null && screeningCriteria.getCreatedAtTo() != null) {
            screening.between(ScreeningEntity_.createdAt.getName(), screeningCriteria.getCreatedAtFrom(), screeningCriteria.getCreatedAtTo());
        }
        if (screeningCriteria.getUpdatedAtFrom() != null && screeningCriteria.getUpdatedAtTo() != null) {
            screening.between(ScreeningEntity_.updatedAt.getName(), screeningCriteria.getUpdatedAtFrom(), screeningCriteria.getUpdatedAtTo());
        }

        return new PageableMapper<>().toDTO(screening.findAll(pageable)
                .map(minimalMapper::toScreeningMinimalDTO));

    }

    @Override
    public ScreeningDetailDTO getScreening(Integer id) {
        ScreeningEntity screening = this.screeningRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found screening"));

        ScreeningDetailDTO screeningDetailDTO = this.screeningMapper.toScreeningDetailDto(screening);
        // TODO: Bypass mapper form now, enhance later
        screeningDetailDTO.setScreeningSeats(this.seatLayoutService.getSeatLayoutByScreeningId(id));

        return screeningDetailDTO;
    }

    @Transactional
    @Override
    public ScreeningDTO createScreening(ScreeningCreationRequest request) {
        ScreeningEntity screening = screeningMapper.toEntity(request);

        if (request.movieId() != null) {
            screening.setMovie(movieRepository.findById(request.movieId())
                    .orElseThrow(() -> new NotFoundException("Not found movie with id: " + request.movieId())));
            if (screening.getStartTime() != null)
                screening.setEndTime(screening.getStartTime()
                        .plusMinutes(screening.getMovie()
                                .getTime()));
        }
        if (request.hallId() != null) {
            HallEntity hall = hallRepository.findById(request.hallId())
                    .orElseThrow(() -> new NotFoundException("Not found hall with id: " + request.hallId()));

            if (hall.getSeats() == null && hall.getSeats()
                    .isEmpty() && hall.getSeats()
                        .contains(null))
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
                    screening.setStatus(ScreeningStatus.NEW.name());
                    screening = screeningRepository.save(screening);
                    final ScreeningEntity savedScreening = screening;
                    List<ScreeningSeatEntity> screeningSeatsCreated = hall.getSeats()
                            .stream()
                            .map(seat -> ScreeningSeatEntity.builder()
                                    .isActive(seat.getIsActive())
                                    .seatColumn(seat.getSeatColumn())
                                    .seatRow(seat.getSeatRow())
                                    .seatNumber(seat.getSeatNumber())
                                    .seatCode(seat.getSeatCode())
                                    .price(seat.getPrice())
                                    // TODO: Enhance Later, Bypass now.
                                    //.seatType(seat.getSeatType())
                                    .isBooked(false)
                                    .screening(savedScreening)
                                    .build())
                            .collect(Collectors.toList());

                    screeningSeatsCreated = screeningSeatRepository.saveAll(screeningSeatsCreated);
                    screeningSeatsCreated.forEach(screening::addScreeningSeat);
                } catch (Exception e) {
                    throw new BadRequestException("Something went wrong!!");
                }

            }
        }
        return screeningMapper.toDTO(screening);
    }

    @Transactional
    @Override
    public ScreeningDTO updateScreening(Integer id, ScreeningUpdateRequest request) {
        ScreeningEntity screening = screeningRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found screening with id: " + id));

        if (!this.isValidStatus(screening.getStatus())) {
            throw new BadRequestException("Cannot update screening with status: " + screening.getStatus());
        }

        HallEntity hall = hallRepository.findById(request.hallId())
                .orElseThrow(() -> new NotFoundException("Not found hall with id: " + request.hallId()));

        LocalDateTime startTime = request.startTime();
        LocalDateTime endTime = request.startTime()
                .plusMinutes(screening.getMovie()
                        .getTime());
        List<ScreeningEntity> overlapTimeScreenings = screeningRepository.findScreeningOverlapTimerInHallExcludeItSelf(startTime, endTime, hall.getId(), screening.getId());

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
        }

        screeningMapper.update(screening, request);

        if (request.movieId() != null) {
            screening.setMovie(movieRepository.findById(request.movieId())
                    .orElseThrow(() -> new NotFoundException("Not found movie with id: " + request.movieId())));
            if (screening.getStartTime() != null)
                screening.setEndTime(screening.getStartTime()
                        .plusMinutes(screening.getMovie()
                                .getTime()));
        }

        if (!request.screeningSeats()
                .isEmpty()) {
            List<ScreeningSeatEntity> screeningSeats = this.screeningSeatRepository.findAllByScreeningId(id);
            List<ScreeningSeatEntity> screeningSeatEntities = this.updateScreeningSeats(request.screeningSeats(), screeningSeats, screening);

            this.screeningSeatRepository.saveAll(screeningSeatEntities);
        }

        screening.setHall(hall);
        ScreeningEntity screeningUpdated = screeningRepository.save(screening);
        return screeningMapper.toDTO(screeningUpdated);
    }

    @Transactional
    @Override
    public void deleteScreening(Integer id) {
        ScreeningEntity screening = screeningRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found screening with id: " + id));
        if (screening != null) {
            List<ScreeningSeatEntity> reservedSeats = screening.getScreeningSeats()
                    .stream()
                    .filter(ScreeningSeatEntity::getIsBooked)
                    .toList();
            if (reservedSeats.isEmpty()) {
                screening.setStatus("cancel");
                screeningRepository.save(screening);
            } else
                throw new BadRequestException("This screening cannot delete because reserved seat(s)");

        }
    }


    private List<ScreeningSeatEntity> updateScreeningSeats(List<ScreeningUpdateSeat> requestUpdateSeats, List<ScreeningSeatEntity> screeningSeats, ScreeningEntity screening) {
        return requestUpdateSeats.stream()
                .map(requestScreeningSeat -> this.enrichScreeningSeats(requestScreeningSeat, screeningSeats, screening))
                .collect(Collectors.toList());
    }

    private ScreeningSeatEntity enrichScreeningSeats(ScreeningUpdateSeat requestScreeningSeat, List<ScreeningSeatEntity> screeningSeatEntities, ScreeningEntity screening) {
        ScreeningSeatEntity screeningSeat = screeningSeatEntities.stream()
                .filter((screeningSeatEntity) -> this.isMatch(screeningSeatEntity, requestScreeningSeat))
                .findFirst()
                .orElseGet(() -> this.updateScreeningSeat(requestScreeningSeat, screening));

        if (!screeningSeat.getIsBooked()) {
            screeningSeat.setIsActive(requestScreeningSeat.isActive());
        }

        if (!screeningSeat.getIsBooked() && requestScreeningSeat.seatPrice() != null) {
            screeningSeat.setPrice(requestScreeningSeat.seatPrice());
        }

        return screeningSeat;
    }

    private boolean isMatch(ScreeningSeatEntity screeningSeat, ScreeningUpdateSeat requestScreeningSeat) {
        return screeningSeat.getSeatRow()
                       .equals(requestScreeningSeat.seatRow()) && screeningSeat.getSeatColumn()
                       .equals(requestScreeningSeat.seatColumn()) && screeningSeat.getSeatNumber()
                       .equals(requestScreeningSeat.seatNumber());

    }

    private ScreeningSeatEntity updateScreeningSeat(ScreeningUpdateSeat requestScreeningSeat, ScreeningEntity screening) {
        StringBuilder stringBuilder = new StringBuilder();
        return ScreeningSeatEntity.builder()
                .screening(screening)
                .seatRow(requestScreeningSeat.seatRow())
                .seatColumn(requestScreeningSeat.seatColumn())
                .seatNumber(requestScreeningSeat.seatNumber())
                .seatCode(
                        stringBuilder
                                .append(requestScreeningSeat.seatRow())
                                .append(requestScreeningSeat.seatNumber())
                                .toString()
                )
                .price(requestScreeningSeat.seatPrice())
                .isBooked(false)
                .isActive(requestScreeningSeat.isActive())
                .build();
    }

    private Boolean isValidStatus(String status) {
        return status.equals(ScreeningStatus.NEW.name());
    }
}
