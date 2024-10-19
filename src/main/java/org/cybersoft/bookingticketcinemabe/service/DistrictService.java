package org.cybersoft.bookingticketcinemabe.service;

import org.cybersoft.bookingticketcinemabe.dto.DistrictDTO;
import org.cybersoft.bookingticketcinemabe.dto.PageableDTO;

public interface DistrictService {
    PageableDTO<?> getDistricts(int pageNo, int pageLimit, String sortBy, Integer provinceId);

    DistrictDTO getDistrict(Integer id);

}
