package com.campro.s3.application;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.campro.s3.application.DTO.ImageListDTO;
import com.campro.s3.infrastructure.S3UploadRepository;
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

    private final S3UploadRepository s3UploadRepository;

    public S3UploadService(S3UploadRepository s3UploadRepository) {
        this.s3UploadRepository = s3UploadRepository;
    }

    public List<ImageListDTO> saveFiles(List<MultipartFile> multipartFile) throws IOException {

        List<ImageListDTO> imageList = new ArrayList<>();
        for (MultipartFile file : multipartFile) {
            if(!file.getOriginalFilename().equals("")) {
                String fileName = createFileName(file.getOriginalFilename());
                imageList.add(new ImageListDTO(fileName, s3UploadRepository.uploadImage(file,fileName)));
            }else{
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"파일이 없습니다.");
            }
        }

        return imageList;

    }


    /**
     *
     * 파일 이름 암호화
     * 원본 이름으로 S3에 이미지를 업로드하게 될 경우 uploadUrl에 원본 이름이 포함되기 때문에
     * 암호화 해서 다른 이름으로 등록
     *
     */
    public String createFileName(String fileName){
        return UUID.randomUUID().toString().concat(getFileExtension(fileName));
    }

    private String getFileExtension(String fileName){
        try{
            return fileName.substring(fileName.lastIndexOf("."));
        } catch (StringIndexOutOfBoundsException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "잘못된 형식의 파일" + "("+ fileName + ") 입니다.");
        }
    }


    public void deleteFile(String fileName){
        s3UploadRepository.deleteImage(fileName);
    }



}
