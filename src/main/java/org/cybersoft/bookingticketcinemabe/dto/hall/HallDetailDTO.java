package org.cybersoft.bookingticketcinemabe.dto.hall;

import lombok.Data;

@Data
public class HallDetailDTO {
    private Integer id;
    private String name;
    private Integer totalSeats;
    private HallBranchDTO branch;
}
