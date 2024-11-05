package org.cybersoft.bookingticketcinemabe.repository;

import org.cybersoft.bookingticketcinemabe.entity.ScreeningSeatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScreeningSeatRepository extends JpaRepository<ScreeningSeatEntity, Integer> {
    List<ScreeningSeatEntity> findAllByIdInAndScreeningId(List<Integer> seatIds, Integer screeningId);
}