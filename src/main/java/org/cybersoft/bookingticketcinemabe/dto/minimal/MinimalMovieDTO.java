package org.cybersoft.bookingticketcinemabe.dto.minimal;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class MinimalMovieDTO extends MinimalDTO {
    private Integer time;
}
