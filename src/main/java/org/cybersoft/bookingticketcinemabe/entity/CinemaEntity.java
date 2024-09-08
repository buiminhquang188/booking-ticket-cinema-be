package org.cybersoft.bookingticketcinemabe.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Entity(name = "cinema")
@Data
public class CinemaEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "total_cinema_hall")
    private Integer totalCinemaHall;

    @Column(name = "image")
    private String image;

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "cinemas")
    private Set<ProvinceEntity> provinces;

    @OneToMany(mappedBy = "cinema")
    private List<BranchEntity> branches;

}
