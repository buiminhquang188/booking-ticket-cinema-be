package org.cybersoft.bookingticketcinemabe.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = "cinema")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
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

    @OneToMany(mappedBy = "cinema")
    private List<CinemaProvinceEntity> cinemaProvinces;

    @OneToMany(mappedBy = "cinema")
    private List<BranchEntity> branches;

}
