package org.cybersoft.bookingticketcinemabe.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity(name = "movie")
@Data
public class MovieEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "rating")
    private byte rating;

    @Column(name = "time")
    private int time;  // Time in minutes or another appropriate unit

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "movie_name")
    private String movieName;

    @Column(name = "poster")
    private String poster;

    @Column(name = "trailer")
    private String trailer;

    @ManyToMany
    @JoinTable(
            name = "branch_movie",
            joinColumns = @JoinColumn(name = "branch_id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id")
    )
    private Set<BranchEntity> branches;

    @OneToMany(mappedBy = "movie")
    private List<ScreeningEntity> screenings;

}
