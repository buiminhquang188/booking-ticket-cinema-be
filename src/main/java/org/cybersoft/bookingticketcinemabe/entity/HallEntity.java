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

    @ManyToOne
    @JoinColumn(name = "branch_id")
    private BranchEntity branch;

    @Column(name = "total_seats")
    private int totalSeats;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "hall")
    private List<HallSeatEntity> hallSeats;

    @OneToMany(mappedBy = "hall")
    private List<ScreeningEntity> screenings;
}
