package org.cybersoft.bookingticketcinemabe.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Entity(name = "branch")
@Getter
@Setter
public class BranchEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "distance")
    private BigDecimal distance;

    @Column(name = "lat")
    private BigDecimal lat;

    @Column(name = "lon")
    private BigDecimal lon;

    @Column(name = "rating")
    private BigDecimal rating;

    @Column(name = "total_cineplex_hall")
    private Integer totalCineplexHall;

    @Column(name = "address")
    private String address;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "link")
    private String link;

    @Column(name = "logo")
    private String logo;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "cinema_id")
    private CinemaEntity cinema;

    @ManyToOne
    @JoinColumn(name = "district_id")
    private DistrictEntity district;

    @OneToMany(mappedBy = "branch")
    private List<HallEntity> halls;

    @ManyToMany(cascade = CascadeType.ALL,
            mappedBy = "branches")
    private Set<MovieEntity> movies;
}
