package org.cybersoft.bookingticketcinemabe.dto.hall;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class HallDetailSeatDTO extends HallDetailDTO {
    private List<List<HallDetailSeatLayoutDTO>> seats;
}
