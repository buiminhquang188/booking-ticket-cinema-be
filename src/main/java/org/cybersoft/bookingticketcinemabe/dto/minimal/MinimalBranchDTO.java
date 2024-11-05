package org.cybersoft.bookingticketcinemabe.dto.minimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MinimalBranchDTO {
    private Integer id;
    private String name;
    private String address;
}
