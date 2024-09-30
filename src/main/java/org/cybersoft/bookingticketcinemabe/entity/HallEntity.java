package org.cybersoft.bookingticketcinemabe.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity(name = "hall")
@Getter
@Setter
public class HallEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "total_seats")
    private Integer totalSeats;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "branch_id")
    private BranchEntity branch;

    @OneToMany(mappedBy = "hall")
    private List<SeatEntity> seats;

    @OneToMany(mappedBy = "hall")
    private List<ScreeningEntity> screenings;
}
