package com.campro.s3.application.DTO;

import java.util.Date;

public class ImageListDTO {

    private final String originFileName ;
    private final String uploadFileName;

    private final Date enroll_date;

    public ImageListDTO(String originFileName, String uploadFileName, Date enroll_date) {
        this.originFileName = originFileName;
        this.uploadFileName = uploadFileName;
        this.enroll_date = enroll_date;
    }

    public String getOriginFileName() {
        return originFileName;
    }

    public String getUploadFileName() {
        return uploadFileName;
    }

    public Date getEnroll_date() {
        return enroll_date;
    }
}
