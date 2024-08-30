package org.cybersoft.bookingticketcinemabe.dto;

import lombok.Data;

@Data
public class UserDTO {
    private int id;

    private String avatar;

    private String email;

    private String firstName;

    private String lastName;

    private String fullName;

    private String role;
}
