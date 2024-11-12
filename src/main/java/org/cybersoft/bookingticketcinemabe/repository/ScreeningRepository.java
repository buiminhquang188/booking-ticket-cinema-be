package org.cybersoft.bookingticketcinemabe.repository;

import org.cybersoft.bookingticketcinemabe.entity.ScreeningEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ScreeningRepository extends JpaRepository<ScreeningEntity, Integer> {
    @Query(value = "SELECT s FROM screening s WHERE s.hall.id = :hallId AND ((s.startTime <= :startTime AND s.endTime >= :startTime) OR (s.startTime <= :endTime AND s.endTime >= :endTime) OR (s.startTime >= :startTime AND s.endTime <= :endTime)) AND s.status IN ('NEW', 'BOOKED', 'IN_PROGRESS')")
    List<ScreeningEntity> findScreeningOverlapTimerInHall(
            @Param("startTime") LocalDateTime startTime,
            @Param("endTime") LocalDateTime endTime,
            @Param("hallId") Integer hallId
    );

    @Query(value = "SELECT s FROM screening s WHERE s.hall.id = :hallId AND ((s.startTime <= :startTime AND s.endTime >= :startTime) OR (s.startTime <= :endTime AND s.endTime >= :endTime) OR (s.startTime >= :startTime AND s.endTime <= :endTime)) AND s.id != :screeningId AND s.status IN ('NEW', 'BOOKED', 'IN_PROGRESS')")
    List<ScreeningEntity> findScreeningOverlapTimerInHallExcludeItSelf(
            @Param("startTime") LocalDateTime startTime,
            @Param("endTime") LocalDateTime endTime,
            @Param("hallId") Integer hallId,
            @Param("screeningId") Integer screeningId
    );
}
