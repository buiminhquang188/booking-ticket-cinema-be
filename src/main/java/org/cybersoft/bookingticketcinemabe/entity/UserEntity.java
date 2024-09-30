package org.cybersoft.bookingticketcinemabe.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity(name = "user")
@Getter
@Setter
public class UserEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "is_email_verified")
    private Boolean isEmailVerified;

    @Column(name = "is_phone_verified")
    private Boolean isPhoneVerified;

    @Column(name = "phone")
    private String phone;

    @Column(name = "role")
    private String role;

    @Column(name = "email")
    private String email;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "user")
    private List<ReservationEntity> reservations;

}
