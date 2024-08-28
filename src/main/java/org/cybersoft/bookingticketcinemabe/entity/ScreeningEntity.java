package org.cybersoft.bookingticketcinemabe.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "screening")
@Data
public class ScreeningEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "hall_id")
    private HallEntity hall;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private MovieEntity movie;

    @OneToMany(mappedBy = "screening")
    private List<ReservationEntity> reservations;

    @OneToMany(mappedBy = "screening")
    private List<SeatReservationEntity> seatReservations;
}
