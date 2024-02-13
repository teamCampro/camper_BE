package com.campro.s3.presentation.controller;

import com.campro.common.controller.ResponseCode;
import com.campro.common.controller.response.ApiResponse;
import com.campro.s3.application.DTO.ImageListDTO;
import com.campro.s3.application.S3UploadService;
import com.campro.s3.presentation.request.FileDeleteRequest;
import org.springframework.http.ResponseEntity;
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



    @PostMapping
    public ResponseEntity<ApiResponse<List<ImageListDTO>>> fileUpload(@RequestPart List<MultipartFile> file) throws IOException {
        List<ImageListDTO> s3ImageList = s3UploadService.saveFiles(file);
        return ResponseEntity.ok().body(ApiResponse.from(ResponseCode.S3_UPLOAD_SUCCESS.getCode(),ResponseCode.S3_UPLOAD_SUCCESS.getMessage(),s3ImageList));
    }


    //삭제
    @DeleteMapping
    public ResponseEntity<ApiResponse<Void>> fileDel(@RequestBody FileDeleteRequest originFileName){
       s3UploadService.deleteFile(originFileName.getFileName());
       return ResponseEntity.ok().body(ApiResponse.from(ResponseCode.S3_DELETE_SUCCESS.getCode(), ResponseCode.S3_DELETE_SUCCESS.getMessage()));
    }
}
