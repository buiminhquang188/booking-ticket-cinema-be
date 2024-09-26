package org.cybersoft.bookingticketcinemabe.repository;

import org.cybersoft.bookingticketcinemabe.entity.CinemaProvinceEntity;
import org.cybersoft.bookingticketcinemabe.entity.key.IdCinemaProvince;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CinemaProvinceRepository extends JpaRepository<CinemaProvinceEntity, IdCinemaProvince> {
}