package org.cybersoft.bookingticketcinemabe.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Entity(name = "districts")
@Getter
@Setter
public class DistrictEntity extends BaseEntity {
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

    @OneToMany(mappedBy = "district")
    private List<BranchEntity> branches;

    @ManyToOne
    @JoinColumn(name = "province_id")
    private ProvinceEntity province;
}
