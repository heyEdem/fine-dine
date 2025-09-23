package com.finedine.authservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class ImageService {
    private final S3Client s3Client;

    @Value("${aws.s3.bucket-name}")
    private String bucketName;
    private final S3Presigner s3Presigner;

    public String uploadImage(MultipartFile file) {
        try {
            String key = "profile-pictures/" + UUID.randomUUID() + "_" + file.getOriginalFilename();
            String s3Url = "https://" + bucketName + ".s3.amazonaws.com/" + key;

            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(key)
                    .contentType(file.getContentType())
                    .build();

            s3Client.putObject(putObjectRequest, RequestBody.fromBytes(file.getBytes()));
            return s3Url;
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload image to S3", e);
        }
    }

    public void deleteImage(String key) {
        if (key == null || key.isEmpty()) {
            throw new IllegalArgumentException("No image keys provided for deletion");
        }
         s3Client.deleteObject(builder -> builder.bucket(bucketName).key(key));
    }


    public String generatePresignedUrl(String key) {
        if (key == null || key.isEmpty()) {
            return key;
        }

        GetObjectPresignRequest presignRequest = GetObjectPresignRequest.builder()
                .signatureDuration(Duration.ofMinutes(10))
                .getObjectRequest(b -> b.bucket(bucketName).key(key))
                .build();

        URL presignedUrl = s3Presigner.presignGetObject(presignRequest).url();

        return (presignedUrl.toString());
    }
}
