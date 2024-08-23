package org.cybersoft.bookingticketcinemabe.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity(name = "movie")
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

    @OneToMany(mappedBy = "movie")
    private List<ScreeningEntity> screenings;

}
