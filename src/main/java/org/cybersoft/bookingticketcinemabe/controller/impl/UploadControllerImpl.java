package org.cybersoft.bookingticketcinemabe.controller.impl;

import lombok.RequiredArgsConstructor;
import org.cybersoft.bookingticketcinemabe.controller.UploadController;
import org.cybersoft.bookingticketcinemabe.payload.response.BaseResponse;
import org.cybersoft.bookingticketcinemabe.service.UploadService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class UploadControllerImpl implements UploadController {

    private final UploadService uploadService;

    @Override
    public ResponseEntity<?> uploadImage(MultipartFile image) {
        String imageUrl = uploadService.uploadImageToCloudflare(image);
        return ResponseEntity.ok(BaseResponse.builder()
                .statusCode(HttpStatus.OK.value())
                .message(HttpStatus.OK.getReasonPhrase())
                .data(imageUrl)
                .build()
        );
    }
}
