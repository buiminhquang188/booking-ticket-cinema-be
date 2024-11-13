package org.cybersoft.bookingticketcinemabe.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "seat")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SeatEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "seat_column")
    private Integer seatColumn;

    @Column(name = "seat_row")
    private String seatRow;

    @Column(name = "seat_number")
    private Integer seatNumber;

    @Column(name = "seat_code")
    private String seatCode;

    @Column(name = "price")
    private Double price;

    @ManyToOne
    @JoinColumn(name = "hall_id")
    private HallEntity hall;

    @ManyToOne
    @JoinColumn(name = "seat_type_id")
    private SeatTypeEntity seatType;
}
