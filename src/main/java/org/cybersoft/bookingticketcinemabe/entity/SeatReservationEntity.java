package org.cybersoft.bookingticketcinemabe.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "seat_reservation")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SeatReservationEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "price")
    private Double price;

    @ManyToOne
    @JoinColumn(name = "reservation_id")
    private ReservationEntity reservation;

    @ManyToOne
    @JoinColumn(name = "screening_id")
    private ScreeningEntity screening;

    @OneToOne
    @JoinColumn(name = "screening_seat_id", referencedColumnName = "id")
    private ScreeningSeatEntity screeningSeat;
}
