package org.cybersoft.bookingticketcinemabe.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity(name = "hall")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HallEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "total_seats")
    private Integer totalSeats;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_id")
    private BranchEntity branch;

    @OneToMany(mappedBy = "hall")
    private List<SeatEntity> seats;

    @OneToMany(mappedBy = "hall")
    private List<ScreeningEntity> screenings;
}
