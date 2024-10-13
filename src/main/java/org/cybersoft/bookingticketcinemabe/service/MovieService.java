package org.cybersoft.bookingticketcinemabe.service;

import org.cybersoft.bookingticketcinemabe.dto.MovieDTO;
import org.cybersoft.bookingticketcinemabe.dto.PageableDTO;
import org.cybersoft.bookingticketcinemabe.payload.request.MovieCreationRequest;

public interface MovieService {
    PageableDTO<?> getMovies(int pageNo, int pageLimit, String sortBy);

    MovieDTO getMovie(int id);

    MovieDTO createMovie(MovieCreationRequest request);
}
