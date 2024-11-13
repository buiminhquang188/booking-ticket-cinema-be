package org.cybersoft.bookingticketcinemabe.service.impl;

import lombok.RequiredArgsConstructor;
import org.cybersoft.bookingticketcinemabe.config.R2CloudlareConfig;
import org.cybersoft.bookingticketcinemabe.exception.BadRequestException;
import org.cybersoft.bookingticketcinemabe.service.UploadService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UploadServiceImpl implements UploadService {

    private final S3Client s3Client;
    private final R2CloudlareConfig r2CloudlareConfig;
    @Value("${cloudflare.r2.public-path}")
    private String publicPath;


    @Override
    public String uploadImageToCloudflare(MultipartFile image) {

        String fileName = generateNewFileName(image.getOriginalFilename());

        try {
            PutObjectRequest request = PutObjectRequest.builder()
                    .bucket(r2CloudlareConfig.getBucketName())
                    .key(fileName)
                    .contentType(image.getContentType())
                    .build();

            s3Client.putObject(request, RequestBody.fromBytes(image.getBytes()));

            return String.format(publicPath + fileName);
        } catch (S3Exception e) {
            throw new BadRequestException("Failed to upload image to Cloudflare");
        } catch (IOException e) {
            throw new RuntimeException("Something went wrong on Cloudflare");
        }
    }

    private String generateNewFileName(String originalFileName) {
        String fileExtension = "";
        String baseName = "file";
        if (originalFileName != null && originalFileName.contains(".")) {
            fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
            baseName = originalFileName.substring(0, originalFileName.lastIndexOf("."));
        }

        String uuid = UUID.randomUUID().toString();

        return baseName + "-" + uuid + fileExtension;
    }
}
