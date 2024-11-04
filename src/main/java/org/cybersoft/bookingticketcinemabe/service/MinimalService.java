package org.cybersoft.bookingticketcinemabe.service;

import org.cybersoft.bookingticketcinemabe.dto.PageableDTO;
import org.cybersoft.bookingticketcinemabe.dto.minimal.MinimalDTO;
import org.cybersoft.bookingticketcinemabe.dto.minimal.MinimalHallDTO;
import org.cybersoft.bookingticketcinemabe.dto.minimal.MinimalMovieDTO;
import org.cybersoft.bookingticketcinemabe.payload.request.minimal.MinimalCriteria;

import java.util.List;

public interface MinimalService {
    PageableDTO<?> getScreenings(MinimalCriteria minimalCriteria);

    PageableDTO<List<MinimalMovieDTO>> getMovies(MinimalCriteria minimalCriteria);

    PageableDTO<?> getBranches(MinimalCriteria minimalCriteria);

    PageableDTO<List<MinimalDTO>> getCinemas(MinimalCriteria minimalCriteria);

    PageableDTO<List<MinimalDTO>> getDistricts(MinimalCriteria minimalCriteria);

    PageableDTO<List<MinimalDTO>> getProvinces(MinimalCriteria minimalCriteria);

    PageableDTO<List<MinimalHallDTO>> getHalls(MinimalCriteria minimalCriteria);
}
