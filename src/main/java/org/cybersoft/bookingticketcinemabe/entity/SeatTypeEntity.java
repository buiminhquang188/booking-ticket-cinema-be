package org.cybersoft.bookingticketcinemabe.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity(name = "seat_type")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SeatTypeEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "seatType")
    private List<SeatEntity> seats;

    @OneToMany(mappedBy = "seatType")
    private List<ScreeningSeatEntity> screeningSeats;
}
