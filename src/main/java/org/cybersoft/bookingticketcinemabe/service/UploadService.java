package org.cybersoft.bookingticketcinemabe.service;

import org.springframework.web.multipart.MultipartFile;

public interface UploadService {

    String uploadImageToCloudflare(MultipartFile image);
}
