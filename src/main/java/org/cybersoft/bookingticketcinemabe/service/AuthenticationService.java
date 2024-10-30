package org.cybersoft.bookingticketcinemabe.service;

import org.cybersoft.bookingticketcinemabe.dto.user.UserDetailsCustom;
import org.cybersoft.bookingticketcinemabe.payload.request.authentication.AuthenticateRequest;

public interface AuthenticationService {
    UserDetailsCustom checkLogin (AuthenticateRequest request);
}
