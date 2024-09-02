package org.cybersoft.bookingticketcinemabe.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "seat")
@Data
public class SeatEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "seat_column")
    private int seatColumn;

    @Column(name = "seat_row")
    private String seatRow;

    @Column(name = "seat_number")
    private int seatNumber;

    @Column(name = "seat_code")
    private String seatCode;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "hall_id")
    private HallEntity hall;

    @OneToMany(mappedBy = "seat")
    private List<SeatReservationEntity> seatReservations;

}
