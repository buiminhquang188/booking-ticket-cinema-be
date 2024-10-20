package org.cybersoft.bookingticketcinemabe.dto.cinema;

import lombok.Data;

@Data
public class CinemaDTO {
    private Integer id;
    private String name;
    private String image;
    private String createdAt;
    private String updatedAt;
}
