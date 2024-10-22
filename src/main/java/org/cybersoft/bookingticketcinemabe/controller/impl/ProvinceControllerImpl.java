package org.cybersoft.bookingticketcinemabe.controller.impl;

import lombok.RequiredArgsConstructor;
import org.cybersoft.bookingticketcinemabe.controller.ProvinceController;
import org.cybersoft.bookingticketcinemabe.dto.PageableDTO;
import org.cybersoft.bookingticketcinemabe.dto.ProvinceDTO;
import org.cybersoft.bookingticketcinemabe.payload.response.BaseResponse;
import org.cybersoft.bookingticketcinemabe.service.ProvinceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProvinceControllerImpl implements ProvinceController {
    private final ProvinceService provinceService;

    @Override
    public ResponseEntity<?> getProvinces(int pageNo, int pageLimit, String sortBy) {
        PageableDTO<?> provinces = provinceService.getProvinces(pageNo, pageLimit, sortBy);
        return ResponseEntity.ok(BaseResponse.builder()
                .statusCode(HttpStatus.OK.value())
                .message(HttpStatus.OK.getReasonPhrase())
                .data(provinces)
                .build()
        );
    }

    @Override
    public ResponseEntity<?> getProvince(Integer id) {
        ProvinceDTO province = provinceService.getProvince(id);
        return ResponseEntity.ok(
                BaseResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message(HttpStatus.OK.getReasonPhrase())
                        .data(province)
                        .build()
        );
    }

}
