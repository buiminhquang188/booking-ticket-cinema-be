package org.cybersoft.bookingticketcinemabe.repository;

import org.cybersoft.bookingticketcinemabe.entity.ScreeningEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScreeningRepository extends JpaRepository<ScreeningEntity, Integer> {
}
