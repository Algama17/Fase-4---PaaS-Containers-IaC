package com.example.demo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "aws")
public class AwsProperties {
    private String accessKeyId;
    private String secretAccessKey;
    private String region;
    private String bucketName;
    
    public AwsProperties() {
    }
    public AwsProperties(String accessKeyId, String secretAccessKey, String region, String bucketName) {
        this.accessKeyId = accessKeyId;
        this.secretAccessKey = secretAccessKey;
        this.region = region;
        this.bucketName = bucketName;
    }
    public String getAccessKeyId() {
        return accessKeyId;
    }
    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }
    public String getSecretAccessKey() {
        return secretAccessKey;
    }
    public void setSecretAccessKey(String secretAccessKey) {
        this.secretAccessKey = secretAccessKey;
    }
    public String getRegion() {
        return region;
    }
    public void setRegion(String region) {
        this.region = region;
    }
    public String getBucketName() {
        return bucketName;
    }
    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }
}