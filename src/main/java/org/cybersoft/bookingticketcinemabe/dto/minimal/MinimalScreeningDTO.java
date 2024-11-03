package org.cybersoft.bookingticketcinemabe.dto.minimal;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MinimalScreeningDTO {
    private Integer id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String movieName;
    private String hallName;
    private String branchName;
    private String branchAddress;
}
