package org.cybersoft.bookingticketcinemabe.repository;

import org.cybersoft.bookingticketcinemabe.entity.SeatReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatReservationRepository extends JpaRepository<SeatReservationEntity, Integer> {
    List<SeatReservationEntity> findAllByScreeningId(Integer screeningId);
}
