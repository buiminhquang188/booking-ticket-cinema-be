package org.cybersoft.bookingticketcinemabe.service;

import org.cybersoft.bookingticketcinemabe.dto.PageableDTO;
import org.cybersoft.bookingticketcinemabe.dto.minimal.MinimalDTO;
import org.cybersoft.bookingticketcinemabe.payload.request.minimal.MinimalCriteria;

import java.util.List;

public interface MinimalService {
    PageableDTO<?> getScreenings(int pageNo, int pageLimit, String sortBy);

    PageableDTO<?> getMovies(MinimalCriteria minimalCriteria);

    PageableDTO<?> getBranches(MinimalCriteria minimalCriteria);

    PageableDTO<List<MinimalDTO>> getCinemas(MinimalCriteria minimalCriteria);

    PageableDTO<List<MinimalDTO>> getDistricts(MinimalCriteria minimalCriteria);
}
