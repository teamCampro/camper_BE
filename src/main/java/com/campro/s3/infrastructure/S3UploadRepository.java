package com.campro.s3.infrastructure;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Repository
public interface S3UploadRepository {

    String uploadImage(MultipartFile file, String fileName) throws IOException;

    void deleteImage(String fileName);
}
