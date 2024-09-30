package org.cybersoft.bookingticketcinemabe.service;

import org.cybersoft.bookingticketcinemabe.dto.MovieDTO;
import org.cybersoft.bookingticketcinemabe.dto.PageableDTO;

public interface MovieService {
    PageableDTO<?> getMovies(int pageNo, int pageLimit, String sortBy);

    MovieDTO getMovie(int id);
}
