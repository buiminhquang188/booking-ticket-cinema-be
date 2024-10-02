package org.cybersoft.bookingticketcinemabe.repository;

import org.cybersoft.bookingticketcinemabe.entity.BranchEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BranchRepository extends JpaRepository<BranchEntity, Integer> {
    List<BranchEntity> findAllByIdInOrCinemaId(List<Integer> id, Integer cinemaId);
}