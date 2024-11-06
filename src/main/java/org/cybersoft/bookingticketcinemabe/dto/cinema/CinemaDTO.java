package org.cybersoft.bookingticketcinemabe.dto.cinema;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class CinemaDTO {
    private Integer id;
    private String name;
    private String image;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private String createdAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private String updatedAt;
}
