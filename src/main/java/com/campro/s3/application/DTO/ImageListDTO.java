package com.campro.s3.application.DTO;

import java.util.Date;

public class ImageListDTO {

    private final String originName ;
    private final String uploadUrl;


    public ImageListDTO(String originName, String uploadUrl) {
        this.originName = originName;
        this.uploadUrl = uploadUrl;
    }

    public String getOriginName() {
        return originName;
    }

    public String getUploadUrl() {
        return uploadUrl;
    }
}
