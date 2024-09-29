package org.cybersoft.bookingticketcinemabe.service.impl;

import org.cybersoft.bookingticketcinemabe.dto.MovieDTO;
import org.cybersoft.bookingticketcinemabe.service.MovieService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class MovieServiceImpl implements MovieService {

    @Override
    public Page<?> getMovies(int pageNo, int pageLimit, String sortBy) {
        return null;
    }

    @Override
    public MovieDTO getMoive(int id) {
        return null;
    }
}
