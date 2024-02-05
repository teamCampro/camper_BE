package com.campro.s3.application;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.campro.s3.application.DTO.ImageListDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Service
public class S3UploadService {
    private final AmazonS3 amazonS3;

    @Value("${spring.cloud.aws.s3.bucket}")
    private String bucket;

    public S3UploadService(AmazonS3 amazonS3) {
        this.amazonS3 = amazonS3;
    }

    public List<ImageListDTO> saveFiles(List<MultipartFile> multipartFile) throws IOException {

        List<ImageListDTO> imageList = new ArrayList<>();
        for (MultipartFile file : multipartFile) {
            if(!file.getOriginalFilename().equals("")) {
                String originalFilename = file.getOriginalFilename();
                String fileName = createFileName(originalFilename);
                ObjectMetadata metadata = new ObjectMetadata();
                metadata.setContentLength(file.getSize());
                metadata.setContentType(file.getContentType());
                amazonS3.putObject(bucket, fileName, file.getInputStream(), metadata);
                imageList.add(new ImageListDTO(originalFilename, amazonS3.getUrl(bucket, fileName).toString(), new Date()));
            }else{
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"파일이 없습니다.");
            }
        }

        return imageList;

    }

    // 파일 이름 암호화
    public String createFileName(String fileName){
        return UUID.randomUUID().toString().concat(getFileExtension(fileName));
    }

    private String getFileExtension(String fileName){
        try{
            return fileName.substring(fileName.lastIndexOf("."));
        } catch (StringIndexOutOfBoundsException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "잘못된 형식의 파일" + fileName + ") 입니다.");
        }
    }


    //파일 삭제
    public void deleteFile(String originFileName){
        DeleteObjectRequest request = new DeleteObjectRequest(bucket,originFileName);
        amazonS3.deleteObject(request);
    }



}
