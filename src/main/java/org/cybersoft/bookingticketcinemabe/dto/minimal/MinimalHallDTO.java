package org.cybersoft.bookingticketcinemabe.dto.minimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MinimalHallDTO {
    private Integer id;
    private String name;
    private MinimalBranchDTO branch;
}
