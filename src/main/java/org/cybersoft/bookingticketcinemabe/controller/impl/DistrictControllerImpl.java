package org.cybersoft.bookingticketcinemabe.controller.impl;

import lombok.RequiredArgsConstructor;
import org.cybersoft.bookingticketcinemabe.controller.DistrictController;
import org.cybersoft.bookingticketcinemabe.dto.DistrictDTO;
import org.cybersoft.bookingticketcinemabe.dto.PageableDTO;
import org.cybersoft.bookingticketcinemabe.payload.response.BaseResponse;
import org.cybersoft.bookingticketcinemabe.service.DistrictService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DistrictControllerImpl implements DistrictController {
    private final DistrictService districtService;

    @Override
    public ResponseEntity<?> getDistricts(int pageNo, int pageLimit, String sortBy, Integer provinceId) {

        PageableDTO<?> districts = districtService.getDistricts(pageNo, pageLimit, sortBy, provinceId);

        return ResponseEntity.ok(BaseResponse.builder()
                .statusCode(HttpStatus.OK.value())
                .message(HttpStatus.OK.getReasonPhrase())
                .data(districts)
                .build()
        );
    }


    @Override
    public ResponseEntity<?> getDistrict(Integer id) {
        DistrictDTO district = districtService.getDistrict(id);
        return ResponseEntity.ok(
                BaseResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message(HttpStatus.OK.getReasonPhrase())
                        .data(district)
                        .build()
        );
    }

}
