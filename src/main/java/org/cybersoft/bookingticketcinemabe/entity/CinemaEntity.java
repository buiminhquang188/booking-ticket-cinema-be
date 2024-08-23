package org.cybersoft.bookingticketcinemabe.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity(name = "cinema")
public class CinemaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "total_cinema_hall")
    private int totalCinemaHall;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "image")
    private String image;

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "cinemas")
    private Set<ProvinceEntity> provinces;

    @OneToMany(mappedBy = "cinema")
    private List<BranchEntity> branches;

}
