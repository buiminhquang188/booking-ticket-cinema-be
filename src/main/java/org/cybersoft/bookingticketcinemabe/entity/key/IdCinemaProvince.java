package org.cybersoft.bookingticketcinemabe.entity.key;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Embeddable
public class IdCinemaProvince implements Serializable {
    @Column(name = "cinema_id")
    private Integer cinemaId;

    @Column(name = "province_id")
    private Integer provinceId;
}
