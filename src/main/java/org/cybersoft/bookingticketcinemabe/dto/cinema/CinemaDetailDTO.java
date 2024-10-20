package org.cybersoft.bookingticketcinemabe.dto.cinema;

import lombok.Data;

import java.util.List;

@Data
public class CinemaDetailDTO {
    private Integer id;
    private String name;
    private String image;
    private List<CinemaDetailBranchDTO> branches;
    private List<CinemaDetailProvinceDTO> provinces;
}
