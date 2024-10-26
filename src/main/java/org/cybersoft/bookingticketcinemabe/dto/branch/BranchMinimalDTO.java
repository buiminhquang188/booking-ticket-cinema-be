package org.cybersoft.bookingticketcinemabe.dto.branch;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BranchMinimalDTO {
    private Integer id;

    private String name;

    private String address;

}
