package org.cybersoft.bookingticketcinemabe.service;

import org.cybersoft.bookingticketcinemabe.dto.PageableDTO;
import org.cybersoft.bookingticketcinemabe.dto.ProvinceDTO;

public interface ProvinceService {
    PageableDTO<?> getProvinces(int pageNo, int pageLimit, String sortBy);

    ProvinceDTO getProvince(Integer id);

}
