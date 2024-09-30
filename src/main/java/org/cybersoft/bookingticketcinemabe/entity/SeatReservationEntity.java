package org.cybersoft.bookingticketcinemabe.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "seat_reservation")
@Getter
@Setter
public class SeatReservationEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "is_reserved")
    private boolean isReserved;

    @Column(name = "price")
    private double price;

    @ManyToOne
    @JoinColumn(name = "seat_id")
    private SeatEntity seat;

    @ManyToOne
    @JoinColumn(name = "reservation_id")
    private ReservationEntity reservation;

    @ManyToOne
    @JoinColumn(name = "screening_id")
    private ScreeningEntity screening;

}
