package org.cybersoft.bookingticketcinemabe.service;

import org.cybersoft.bookingticketcinemabe.dto.PageableDTO;

public interface MinimalService {
    PageableDTO<?> getScreenings(int pageNo, int pageLimit, String sortBy);

    PageableDTO<?> getMovies(int pageNo, int pageLimit, String sortBy);

    PageableDTO<?> getBranches(int pageNo, int pageLimit, String sortBy);
}
