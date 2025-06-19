package com.example.demo.service;

import com.example.demo.AwsProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.auth.credentials.*;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class S3Service {

    private final S3Client s3Client;
    private final String bucketName;

    public S3Service(AwsProperties awsProperties) {
        this.bucketName = awsProperties.getBucketName();
        this.s3Client = S3Client.builder()
            .region(Region.of(awsProperties.getRegion()))
            .credentialsProvider(
                StaticCredentialsProvider.create(
                    AwsBasicCredentials.create(
                        awsProperties.getAccessKeyId(),
                        awsProperties.getSecretAccessKey()
                    )
                )
            )
            .build();
    }

    public void uploadFile(MultipartFile file) throws IOException {
        Path tempFile = Files.createTempFile("upload-", file.getOriginalFilename());
        file.transferTo(tempFile.toFile());

        s3Client.putObject(
            PutObjectRequest.builder()
                .bucket(bucketName)
                .key(file.getOriginalFilename())
                .contentType(file.getContentType())
                .build(),
            tempFile
        );

        Files.delete(tempFile);
    }
}
