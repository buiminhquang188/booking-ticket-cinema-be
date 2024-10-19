package org.cybersoft.bookingticketcinemabe.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "screening")
@Getter
@Setter
public class ScreeningEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

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


    public void addReservation(ReservationEntity reservation) {
        this.reservations.add(reservation);
        reservation.setScreening(this);
    }

    public void removeReservation(ReservationEntity reservation) {
        this.reservations.remove(reservation);
        reservation.setScreening(null);
    }

    public void addSeatReservation(SeatReservationEntity seatReservation) {
        this.seatReservations.add(seatReservation);
        seatReservation.setScreening(this);
    }

    public void removeSeatReservation(SeatReservationEntity seatReservation) {
        this.seatReservations.remove(seatReservation);
        seatReservation.setScreening(null);
    }
}
