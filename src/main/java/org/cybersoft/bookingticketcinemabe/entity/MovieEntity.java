package org.cybersoft.bookingticketcinemabe.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity(name = "movie")
@Getter
@Setter
public class MovieEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "rating")
    private Byte rating;

    @Column(name = "time")
    private Integer time;  // Time in minutes or another appropriate unit

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "movie_name")
    private String movieName;

    @Column(name = "poster")
    private String poster;

    @Column(name = "trailer")
    private String trailer;

    @ManyToMany
    @JoinTable(
            name = "branch_movie",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "branch_id")
    )
    private Set<BranchEntity> branches;

    @OneToMany(mappedBy = "movie")
    private List<ScreeningEntity> screenings;

}
