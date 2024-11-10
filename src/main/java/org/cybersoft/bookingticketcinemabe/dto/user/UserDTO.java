package org.cybersoft.bookingticketcinemabe.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cybersoft.bookingticketcinemabe.dto.ReservationUserDTO;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Integer id;
    private String avatar;
    private String email;
    private String phone;
    private String firstName;
    private String lastName;
    private String fullName;
    private String role;
    private List<ReservationUserDTO> reservations;
}
