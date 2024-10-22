package org.cybersoft.bookingticketcinemabe.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.cybersoft.bookingticketcinemabe.entity.key.IdCinemaProvince;

@Entity(name = "cinema_provinces")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CinemaProvinceEntity {
    @EmbeddedId
    private IdCinemaProvince id;

    @ManyToOne
    @JoinColumn(name = "cinema_id", updatable = false, insertable = false)
    private CinemaEntity cinema;

    @ManyToOne
    @JoinColumn(name = "province_id", updatable = false, insertable = false)
    private ProvinceEntity province;
}
