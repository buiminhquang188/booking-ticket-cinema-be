package org.cybersoft.bookingticketcinemabe.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "hall")
@Data
public class HallEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "total_seats")
    private int totalSeats;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

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
