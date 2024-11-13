package org.cybersoft.bookingticketcinemabe.service;

import org.cybersoft.bookingticketcinemabe.dto.PageableDTO;
import org.cybersoft.bookingticketcinemabe.dto.movie.MovieDTO;
import org.cybersoft.bookingticketcinemabe.payload.request.movie.MovieCreationRequest;
import org.cybersoft.bookingticketcinemabe.payload.request.movie.MovieCriteria;
import org.cybersoft.bookingticketcinemabe.payload.request.movie.MovieUpdateRequest;

public interface MovieService {
    PageableDTO<?> getMovies(MovieCriteria movieCriteria);

    MovieDTO getMovie(Integer id);

    MovieDTO createMovie(MovieCreationRequest request);

    MovieDTO updateMovie(Integer id, MovieUpdateRequest request);

    void deleteMovie(Integer id);

}
