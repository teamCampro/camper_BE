package com.campro.s3.presentation.controller;


import com.campro.s3.application.DTO.ImageListDTO;
import com.campro.s3.application.S3UploadService;
import com.campro.s3.presentation.request.FileDeleteRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/image")
public class S3Controller {
    
    private final S3UploadService s3UploadService;

    public S3Controller(S3UploadService s3UploadService) {
        this.s3UploadService = s3UploadService;
    }


    //등록
    @PostMapping
    public String fileUpload(@RequestPart List<MultipartFile> file) throws IOException {
        s3UploadService.saveFiles(file);
        return "ok";
    }


    //삭제
    @DeleteMapping
    public String fileDel(@RequestBody FileDeleteRequest originFileName){
       s3UploadService.deleteFile(originFileName.getFileName());
       return "OK";
    }
}
