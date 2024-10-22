package org.cybersoft.bookingticketcinemabe.repository;

import org.cybersoft.bookingticketcinemabe.entity.SeatTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatTypeRepository extends JpaRepository<SeatTypeEntity, Integer> {
}