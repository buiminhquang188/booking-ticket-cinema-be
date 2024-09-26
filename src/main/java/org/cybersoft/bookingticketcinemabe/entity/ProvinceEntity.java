package org.cybersoft.bookingticketcinemabe.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity(name = "provinces")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProvinceEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "code_name")
    private String codeName;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "full_name_en")
    private String fullNameEn;

    @Column(name = "name")
    private String name;

    @Column(name = "name_en")
    private String nameEn;

    @OneToMany(mappedBy = "province")
    private List<DistrictEntity> districts;

    @OneToMany(mappedBy = "province")
    private List<CinemaProvinceEntity> provinceCinemas;
}

