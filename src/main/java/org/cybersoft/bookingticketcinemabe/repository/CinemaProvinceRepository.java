package org.cybersoft.bookingticketcinemabe.repository;

import org.cybersoft.bookingticketcinemabe.entity.CinemaProvinceEntity;
import org.cybersoft.bookingticketcinemabe.entity.key.IdCinemaProvince;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CinemaProvinceRepository extends JpaRepository<CinemaProvinceEntity, IdCinemaProvince> {
    List<CinemaProvinceEntity> findAllByCinemaId(Integer cinemaId);

    CinemaProvinceEntity findByCinemaIdAndProvinceId(Integer cinemaId, Integer provinceId);
}