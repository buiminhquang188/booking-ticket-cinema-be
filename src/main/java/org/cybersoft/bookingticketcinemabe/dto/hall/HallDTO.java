package org.cybersoft.bookingticketcinemabe.dto.hall;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HallDTO {
    private Integer id;
    private Integer totalSeats;
    private String name;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
