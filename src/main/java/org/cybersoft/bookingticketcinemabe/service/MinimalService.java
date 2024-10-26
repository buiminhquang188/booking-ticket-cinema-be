package org.cybersoft.bookingticketcinemabe.service;

import org.cybersoft.bookingticketcinemabe.dto.MinimalDTO;
import org.cybersoft.bookingticketcinemabe.dto.PageableDTO;
import org.cybersoft.bookingticketcinemabe.payload.request.MinimalCriteria;

import java.util.List;

public interface MinimalService {
    PageableDTO<?> getScreenings(int pageNo, int pageLimit, String sortBy);

    PageableDTO<?> getMovies(int pageNo, int pageLimit, String sortBy);

    PageableDTO<?> getBranches(int pageNo, int pageLimit, String sortBy);

    PageableDTO<List<MinimalDTO>> getCinemas(MinimalCriteria minimalCriteria);

    PageableDTO<List<MinimalDTO>> getDistricts(MinimalCriteria minimalCriteria);
}
