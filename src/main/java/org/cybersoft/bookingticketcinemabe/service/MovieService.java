package org.cybersoft.bookingticketcinemabe.service;

import org.cybersoft.bookingticketcinemabe.dto.MovieDTO;
import org.springframework.data.domain.Page;

public interface MovieService {
    Page<?> getMovies(int pageNo, int pageLimit, String sortBy);

    MovieDTO getMoive(int id);
}
