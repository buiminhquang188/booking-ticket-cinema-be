package org.cybersoft.bookingticketcinemabe.service;

import org.springframework.data.domain.Page;

public interface CinemaService {
    Page<?> getCinemas(int pageNo, int pageSize, String name);
}
