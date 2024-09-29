package org.cybersoft.bookingticketcinemabe.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DistrictDTO {
    private Integer id;

    private String codeName;

    private String fullName;

    private String fullNameEn;

    private String name;

    private String nameEn;

}
