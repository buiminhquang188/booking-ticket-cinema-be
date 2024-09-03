package org.cybersoft.bookingticketcinemabe.entity.key;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class IdCinemaProvince implements Serializable {
    @Column(name = "cinema_id")
    private Integer cinemaId;

    @Column(name = "province_id")
    private Integer provinceId;
}