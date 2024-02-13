package com.campro.s3.infrastructure;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class S3UploadRepositoryImpl implements S3UploadRepository{

    private final AmazonS3 amazonS3;

    @Value("${spring.cloud.aws.s3.bucket}")
    private String bucket;

    public S3UploadRepositoryImpl(AmazonS3 amazonS3) {
        this.amazonS3 = amazonS3;
    }

    @Override
    public String uploadImage(MultipartFile file, String fileName) throws IOException {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        metadata.setContentType(file.getContentType());
        amazonS3.putObject(bucket, fileName, file.getInputStream(), metadata);
        return amazonS3.getUrl(bucket, fileName).toString();
    }

    @Override
    public void deleteImage(String fileName) {
        DeleteObjectRequest request = new DeleteObjectRequest(bucket,fileName);
        amazonS3.deleteObject(request);
    }
}
