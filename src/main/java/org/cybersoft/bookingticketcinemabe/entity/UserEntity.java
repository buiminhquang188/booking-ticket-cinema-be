package org.cybersoft.bookingticketcinemabe.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity(name = "user")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "is_email_verified")
    Boolean isEmailVerified;

    @Column(name = "is_phone_verified")
    Boolean isPhoneVerified;

    @Column(name = "phone")
    String phone;

    @Column(name = "role")
    String role;

    @Column(name = "email")
    String email;

    @Column(name = "first_name")
    String firstName;

    @Column(name = "last_name")
    String lastName;

    @Column(name = "full_name")
    String fullName;

    @Column(name = "avatar")
    String avatar;

    @Column(name = "password")
    String password;

    @OneToMany(mappedBy = "user")
    List<ReservationEntity> reservations;

}
