package org.cybersoft.bookingticketcinemabe.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.S3Configuration;

import java.net.URI;

@Configuration
public class R2CloudlareConfig {
    @Value("${cloudflare.r2.access-key}")
    private String accessKey;

    @Value("${cloudflare.r2.secret-key}")
    private String secrectKey;

    @Value("${cloudflare.r2.region}")
    private String region;

    @Value("${cloudflare.r2.endpoint}")
    private String endpoint;

    @Value("${cloudflare.r2.bucket}")
    @Getter
    private String bucketName;

    @Bean
    public S3Client s3Client() {
        AwsBasicCredentials credentials = AwsBasicCredentials.create(accessKey, secrectKey);
        StaticCredentialsProvider credentialsProvider = StaticCredentialsProvider.create(credentials);
        Region regionS3 = Region.of(region);
        S3Configuration configuration = S3Configuration.builder().pathStyleAccessEnabled(true).build();

        return S3Client.builder()
                .credentialsProvider(credentialsProvider)
                .region(regionS3)
                .serviceConfiguration(configuration)
                .endpointOverride(URI.create(endpoint))
                .build();
    }
}
