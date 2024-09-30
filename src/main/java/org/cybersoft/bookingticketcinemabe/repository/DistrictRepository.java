package org.cybersoft.bookingticketcinemabe.repository;

import org.cybersoft.bookingticketcinemabe.entity.DistrictEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistrictRepository extends JpaRepository<DistrictEntity, Integer> {
}
