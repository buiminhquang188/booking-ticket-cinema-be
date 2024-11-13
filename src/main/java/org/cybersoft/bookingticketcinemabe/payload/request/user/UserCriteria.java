package org.cybersoft.bookingticketcinemabe.payload.request.user;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.cybersoft.bookingticketcinemabe.payload.request.pagination.Pagination;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserCriteria extends Pagination {
    private Integer id;

    private String phone;

    private String role;

    private String email;

    private String firstName;

    private String lastName;

    private String fullName;
}
