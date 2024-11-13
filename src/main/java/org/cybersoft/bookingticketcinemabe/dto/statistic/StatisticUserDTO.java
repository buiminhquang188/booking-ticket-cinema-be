package org.cybersoft.bookingticketcinemabe.dto.statistic;

import lombok.Data;

@Data
public class StatisticUserDTO {
    private Integer total;
    private Integer totalUser;
    private Integer totalAdmin;
    private Integer totalSuperAdmin;
}
