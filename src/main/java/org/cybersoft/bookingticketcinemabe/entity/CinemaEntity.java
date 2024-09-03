package org.cybersoft.bookingticketcinemabe.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "cinema")
@Data
public class CinemaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "total_cinema_hall")
    private int totalCinemaHall;

    @Column(name = "image")
    private String image;

    @Column(name = "name")
    private String name;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "cinema")
    private List<CinemaProvinceEntity> cinemaProvinces;

    @OneToMany(mappedBy = "cinema")
    private List<BranchEntity> branches;

}
